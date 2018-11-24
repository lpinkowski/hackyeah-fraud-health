package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieWithJPGGroup;

import java.util.List;

public interface ICD10Service {
    
    List<RozpoznanieWithJPGGroup> przyrostyRozpoznanRokDoRoku(); 
}
