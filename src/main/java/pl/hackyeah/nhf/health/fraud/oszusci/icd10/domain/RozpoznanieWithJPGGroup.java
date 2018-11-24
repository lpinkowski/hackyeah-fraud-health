package pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RozpoznanieWithJPGGroup {

    String ICD10;

    int year;

    int numberOfCases;
}
