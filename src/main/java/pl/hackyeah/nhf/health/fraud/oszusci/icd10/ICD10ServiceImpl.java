package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.AggregatedAverageValueGroupByICD10CodeYear;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.Fraud;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RecognitionWithJPGGroup;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieRelativeIncrease;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<RozpoznanieRelativeIncrease> findRelativeIncreaseYearToYear() {
        List<RecognitionWithJPGGroup> icd10WithJPGGroupByYears = repo.getICD10WithJPGGroupByYearsOrderByCodeYearList(); // we get it ordered by free

        String actualICD10 = null;
        RecognitionWithJPGGroup previous = null;
        List<RozpoznanieRelativeIncrease> rezpoznanieRelativeIcreases = new ArrayList<>(icd10WithJPGGroupByYears.size());
        for (RecognitionWithJPGGroup actualGroup : icd10WithJPGGroupByYears) {
            RozpoznanieRelativeIncrease result;
            if (actualICD10 == null || !actualICD10.equals(actualGroup.getICD10())) {
                actualICD10 = actualGroup.getICD10();
                result = new RozpoznanieRelativeIncrease(actualGroup, ZERO);
            } else {
                result = new RozpoznanieRelativeIncrease(actualGroup, previous.getNumberOfCases() > CUT_OFF ? (double) actualGroup.getNumberOfCases() / (double) previous.getNumberOfCases() : ZERO);
            }
            previous = actualGroup;
            rezpoznanieRelativeIcreases.add(result);
        }
        rezpoznanieRelativeIcreases.sort(Comparator.comparing(RozpoznanieRelativeIncrease::getRelativeIncrease).reversed());

        return rezpoznanieRelativeIcreases.stream()
                .filter(r -> r.getRelativeIncrease() > PERCENT_OF_DIAGNOSIS_INCREASED)
                .collect(toList());
    }


    public List<Fraud> findFraud(List<RozpoznanieRelativeIncrease> przyrostyRozpoznanRokDoRoku) {
        List<Fraud> confirmed = new ArrayList<>();
        for (RozpoznanieRelativeIncrease rozpoznanieRelativeIncrease : przyrostyRozpoznanRokDoRoku) {
            List<AggregatedAverageValueGroupByICD10CodeYear> podejrzanaLista = repo.getAggregatedAverageValueGroupByICD10CodeYearList(rozpoznanieRelativeIncrease.getICD10(), rozpoznanieRelativeIncrease.getYear());

            Map<String, List<AggregatedAverageValueGroupByICD10CodeYear>> collect = podejrzanaLista.stream().collect(Collectors.groupingBy(AggregatedAverageValueGroupByICD10CodeYear::getJgpKod));

            List<Fraud> confirmedList = collect.values().stream()
                    .filter(list -> {
                        if (list.size() > 1) {
                            AggregatedAverageValueGroupByICD10CodeYear value1 = list.get(0);
                            AggregatedAverageValueGroupByICD10CodeYear value2 = list.get(1);
                            if (value1.getRok() < value2.getRok()) {
                                return value1.getSredniaArytmetycznaSredniejWartosciGrupy() * PERCENT_OF_JGP_VALUE_INCREASE < value2.getSredniaArytmetycznaSredniejWartosciGrupy();
                            } else {
                                return value2.getSredniaArytmetycznaSredniejWartosciGrupy() * PERCENT_OF_JGP_VALUE_INCREASE < value1.getSredniaArytmetycznaSredniejWartosciGrupy();
                            }

                        } else return false;
                    })
                    .map(d -> new Fraud(d.get(0).getJgpKod(), rozpoznanieRelativeIncrease.getICD10(), selectYear(d), actualGrowth(d.get(0), d.get(1)), rozpoznanieRelativeIncrease.getRelativeIncrease()))
                    .collect(toList());
            confirmed.addAll(confirmedList);
        }
        confirmed.sort(Comparator.comparing(Fraud::getJgpCode));
        return confirmed;
    }

    private int selectYear(List<AggregatedAverageValueGroupByICD10CodeYear> d) {
        return Math.max(d.get(0).getRok(), d.get(1).getRok());
    }

    private double actualGrowth(AggregatedAverageValueGroupByICD10CodeYear value1, AggregatedAverageValueGroupByICD10CodeYear value2) {
        if (value1.getRok() < value2.getRok()) {
            return value2.getSredniaArytmetycznaSredniejWartosciGrupy() / value1.getSredniaArytmetycznaSredniejWartosciGrupy();
        } else {
            return value1.getSredniaArytmetycznaSredniejWartosciGrupy() / value2.getSredniaArytmetycznaSredniejWartosciGrupy();
        }
    }
}

