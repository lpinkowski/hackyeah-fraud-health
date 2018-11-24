package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import java.util.List;

public interface ICD10Service {
    
    List<ICD10ServiceImpl.RozpoznanieRelativeIncrease> przyrostyRozpoznanRokDoRoku(); 
}
