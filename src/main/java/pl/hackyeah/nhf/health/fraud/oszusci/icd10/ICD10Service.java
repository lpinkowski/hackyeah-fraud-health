package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.Fraud;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieRelativeIncrease;

import java.util.List;

public interface ICD10Service {

    List<RozpoznanieRelativeIncrease> findRelativeIncreaseYearToYear();

    List<Fraud> findFraud(List<RozpoznanieRelativeIncrease> przyrostyRozpoznanRokDoRoku);
}
