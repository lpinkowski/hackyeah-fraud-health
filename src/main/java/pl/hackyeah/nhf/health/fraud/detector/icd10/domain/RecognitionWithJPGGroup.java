package pl.hackyeah.nhf.health.fraud.detector.icd10.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecognitionWithJPGGroup {
    
    String ICD10;
    int year;
    int numberOfCases;
}
