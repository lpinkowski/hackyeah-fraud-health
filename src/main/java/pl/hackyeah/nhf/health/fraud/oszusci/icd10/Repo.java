package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieWithJPGGroup;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.ZagregowaneSrednieWarotsciGrupByICD10KodByRok;

import java.util.List;

public interface Repo {
    List<RozpoznanieWithJPGGroup> getICD10WithJPGGroupByYearsOrderByKodRok();
    List<ZagregowaneSrednieWarotsciGrupByICD10KodByRok> getTutek(String kodRozpoznania, int rok);
}
