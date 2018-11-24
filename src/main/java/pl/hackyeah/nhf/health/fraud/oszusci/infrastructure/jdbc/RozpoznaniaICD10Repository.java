package pl.hackyeah.nhf.health.fraud.oszusci.infrastructure.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.hackyeah.nhf.health.fraud.oszusci.domain.RozpoznanieWithJPGGroup;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RozpoznaniaICD10Repository implements Repo{

    private final JdbcTemplate jdbcTemplate;

    public List<RozpoznanieWithJPGGroup> getICD10WithJPGGroupByYears() {
        return jdbcTemplate.query("SELECT w.Kod, w.Rok, sum(w.liczba_wystapien) FROM (\n" +
                "SELECT r.Kod, jgp.Rok , (jgp.Liczba_pacjentow * r.Udzial)/100 as liczba_wystapien FROM health.Rozpoznania_ICD10 as r \n" +
                "LEFT JOIN health.Grupy_JGP as jgp\n" +
                "ON r.Id_zestawienia = jgp.Id_zestawienia) as w\n" +
                "GROUP BY w.Kod, w.Rok order by w.Kod, w.Rok desc", (rs, rowNum)-> new RozpoznanieWithJPGGroup(rs.getString(1), rs.getInt(2), (int)rs.getDouble(3)));
    }
}
