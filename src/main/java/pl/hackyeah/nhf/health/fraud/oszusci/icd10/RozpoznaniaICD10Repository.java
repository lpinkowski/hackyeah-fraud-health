package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RozpoznaniaICD10Repository implements Repo {

    private final JdbcTemplate jdbcTemplate;

    public List<pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieWithJPGGroup> getICD10WithJPGGroupByYearsOrderByKodRok() {
        //@formatter:off
        return jdbcTemplate.query(
                "SELECT w.Kod, w.Rok, sum(w.liczba_wystapien) " + 
                    "FROM(" +
                        "SELECT r.Kod, jgp.Rok, (jgp.Liczba_pacjentow * r.Udzial)/100 as liczba_wystapien " +
                         "FROM health.Rozpoznania_ICD10 as r " +
                         "LEFT JOIN health.Grupy_JGP as jgp " +
                         "ON r.Id_zestawienia = jgp.Id_zestawienia) as w " +
                    "GROUP BY w.Kod, w.Rok order by w.Kod, w.Rok ", (rs, rowNum)-> new pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieWithJPGGroup(rs.getString(1), rs.getInt(2), (int)rs.getDouble(3)));
        //@formatter:on
    }
    
    
}
