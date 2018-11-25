package pl.hackyeah.nhf.health.fraud.detector.icd10.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AggregatedAverageValueGroupByICD10CodeYear {

    int year;
    String jgpCode;
    double averageArithmeticFromAverageOfGroupValue;
}
