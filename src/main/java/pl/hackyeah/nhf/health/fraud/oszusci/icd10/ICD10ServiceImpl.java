package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieWithJPGGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ICD10ServiceImpl implements ICD10Service {

    private final Repo repo;

    @Override
    public List<RozpoznanieRelativeIncrease> przyrostyRozpoznanRokDoRoku() {
        List<RozpoznanieWithJPGGroup> icd10WithJPGGroupByYears = repo.getICD10WithJPGGroupByYearsOrderByKodRok(); // we get it ordered by free

        String actualICD10 = null;
        RozpoznanieWithJPGGroup previous = null;
        List<RozpoznanieRelativeIncrease> rezpoznanieRelativeIcreases = new ArrayList<>(icd10WithJPGGroupByYears.size());
        for (RozpoznanieWithJPGGroup actualGroup : icd10WithJPGGroupByYears) {
            RozpoznanieRelativeIncrease result;
            if (actualICD10 == null || !actualICD10.equals(actualGroup.getICD10())) {
                actualICD10 = actualGroup.getICD10();
                result = new RozpoznanieRelativeIncrease(actualGroup, 0);
            } else {
                result = new RozpoznanieRelativeIncrease(actualGroup, previous.getNumberOfCases() > 200 ? (double) actualGroup.getNumberOfCases() / (double) previous.getNumberOfCases() : 0); //TODO te odciecie > 200 trzeba sparametryzowac
            }
            previous = actualGroup;
            rezpoznanieRelativeIcreases.add(result);
        }
        rezpoznanieRelativeIcreases.sort(Comparator.comparing(RozpoznanieRelativeIncrease::getRelativeIncrease).reversed());

        return rezpoznanieRelativeIcreases.stream()
                //.filter(r->r.getRelativeIncrease()<=2)
                .limit(100)
                .collect(Collectors.toList());
    }

    @Getter
    public class RozpoznanieRelativeIncrease {

        private String ICD10;

        private int year;

        double relativeIncrease;

        public RozpoznanieRelativeIncrease(RozpoznanieWithJPGGroup actualGroup, double relativeIncrease) {
            this.ICD10 = actualGroup.getICD10();
            this.year = actualGroup.getYear();
            this.relativeIncrease = relativeIncrease;
        }
    }
}

