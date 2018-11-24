package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieWithJPGGroup;

import java.util.List;

@Service
@AllArgsConstructor
public class ICD10ServiceImpl implements ICD10Service {

    private final Repo repo;

    @Override
    public List<RozpoznanieWithJPGGroup> przyrostyRozpoznanRokDoRoku() {
        List<RozpoznanieWithJPGGroup> icd10WithJPGGroupByYears = repo.getICD10WithJPGGroupByYears();

        //TODO 
        return icd10WithJPGGroupByYears;
    }
}
