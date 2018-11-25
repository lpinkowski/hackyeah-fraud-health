package pl.hackyeah.nhf.health.fraud.detector.icd10;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.AggregatedAverageValueGroupByICD10CodeYear;
import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.Fraud;
import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.RecognitionWithJPGGroup;
import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.RecognitionRelativeIncrease;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ICD10ServiceImpl implements ICD10Service {

    private static final double PERCENT_OF_JGP_VALUE_INCREASE = 1.15;
    private static final double PERCENT_OF_DIAGNOSIS_INCREASED = 1.1;
    private static final int CUT_OFF = 200;
    private static final int ZERO = 0;

    private final Repo repo;

    @Override
    public List<RecognitionRelativeIncrease> findRelativeIncreaseYearToYear() {
        List<RecognitionWithJPGGroup> icd10WithJPGGroupByYears = repo.getICD10WithJPGGroupByYearsOrderByCodeYearList(); // we get it ordered by free

        String actualICD10 = null;
        RecognitionWithJPGGroup previous = null;
        List<RecognitionRelativeIncrease> recognitionRelativeIncreases = new ArrayList<>(icd10WithJPGGroupByYears.size());
        for (RecognitionWithJPGGroup actualGroup : icd10WithJPGGroupByYears) {
            RecognitionRelativeIncrease result;
            if (actualICD10 == null || !actualICD10.equals(actualGroup.getICD10())) {
                actualICD10 = actualGroup.getICD10();
                result = new RecognitionRelativeIncrease(actualGroup, ZERO);
            } else {
                result = new RecognitionRelativeIncrease(actualGroup, previous.getNumberOfCases() > CUT_OFF ? (double) actualGroup.getNumberOfCases() / (double) previous.getNumberOfCases() : ZERO);
            }
            previous = actualGroup;
            recognitionRelativeIncreases.add(result);
        }
        recognitionRelativeIncreases.sort(Comparator.comparing(RecognitionRelativeIncrease::getRelativeIncrease).reversed());

        return recognitionRelativeIncreases.stream()
                .filter(r -> r.getRelativeIncrease() > PERCENT_OF_DIAGNOSIS_INCREASED)
                .collect(toList());
    }

    public List<Fraud> findFraud(List<RecognitionRelativeIncrease> recognitionRelativeIncreases) {
        List<Fraud> confirmed = new ArrayList<>();
        for (RecognitionRelativeIncrease relativeIncrease : recognitionRelativeIncreases) {
            List<AggregatedAverageValueGroupByICD10CodeYear> suspiciousList = repo.getAggregatedAverageValueGroupByICD10CodeYearList(relativeIncrease.getICD10(), relativeIncrease.getYear());

            Map<String, List<AggregatedAverageValueGroupByICD10CodeYear>> collect = suspiciousList.stream().collect(groupingBy(AggregatedAverageValueGroupByICD10CodeYear::getJgpCode));

            List<Fraud> confirmedList = collect.values().stream()
                    .filter(list -> {
                        if (list.size() > 1) {
                            AggregatedAverageValueGroupByICD10CodeYear value1 = list.get(0);
                            AggregatedAverageValueGroupByICD10CodeYear value2 = list.get(1);
                            if (value1.getYear() < value2.getYear()) {
                                return value1.getAverageArithmeticFromAverageOfGroupValue() * PERCENT_OF_JGP_VALUE_INCREASE < value2.getAverageArithmeticFromAverageOfGroupValue();
                            } else {
                                return value2.getAverageArithmeticFromAverageOfGroupValue() * PERCENT_OF_JGP_VALUE_INCREASE < value1.getAverageArithmeticFromAverageOfGroupValue();
                            }

                        } else return false;
                    })
                    .map(d -> new Fraud(d.get(0).getJgpCode(), relativeIncrease.getICD10(), selectYear(d), actualGrowth(d.get(0), d.get(1)), relativeIncrease.getRelativeIncrease()))
                    .collect(toList());
            confirmed.addAll(confirmedList);
        }
        confirmed.sort(Comparator.comparing(Fraud::getJgpCode));
        return confirmed;
    }

    private int selectYear(List<AggregatedAverageValueGroupByICD10CodeYear> d) {
        return Math.max(d.get(0).getYear(), d.get(1).getYear());
    }

    private double actualGrowth(AggregatedAverageValueGroupByICD10CodeYear value1, AggregatedAverageValueGroupByICD10CodeYear value2) {
        if (value1.getYear() < value2.getYear()) {
            return value2.getAverageArithmeticFromAverageOfGroupValue() / value1.getAverageArithmeticFromAverageOfGroupValue();
        } else {
            return value1.getAverageArithmeticFromAverageOfGroupValue() / value2.getAverageArithmeticFromAverageOfGroupValue();
        }
    }
}