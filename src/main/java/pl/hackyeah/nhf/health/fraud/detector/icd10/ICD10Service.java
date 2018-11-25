package pl.hackyeah.nhf.health.fraud.detector.icd10;

import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.Fraud;
import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.RecognitionRelativeIncrease;

import java.util.List;

public interface ICD10Service {

    List<RecognitionRelativeIncrease> findRelativeIncreaseYearToYear();

    List<Fraud> findFraud(List<RecognitionRelativeIncrease> recognitionRelativeIncreases);
}
