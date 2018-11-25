package pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AggregatedAverageValueGroupByICD10CodeYear {

    int rok;
    String jgpKod;
    double sredniaArytmetycznaSredniejWartosciGrupy;
}
