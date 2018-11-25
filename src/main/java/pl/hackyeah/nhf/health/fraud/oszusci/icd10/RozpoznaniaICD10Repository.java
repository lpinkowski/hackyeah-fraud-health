package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.ZagregowaneSrednieWarotsciGrupByICD10KodByRok;

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
                         "FROM health.rozpoznania_icd10 as r " +
                         "LEFT JOIN health.grupy_jgp as jgp " +
                         "ON r.Id_zestawienia = jgp.Id_zestawienia) as w " +
                    "GROUP BY w.Kod, w.Rok order by w.Kod, w.Rok ", (rs, rowNum)-> new pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieWithJPGGroup(rs.getString(1), rs.getInt(2), (int)rs.getDouble(3)));
        //@formatter:on
    }

    public List<ZagregowaneSrednieWarotsciGrupByICD10KodByRok> getTutek(String kodRozpoznania, int rok) {
        //@formatter:off
        return jdbcTemplate.query(
                "SELECT w.Rok, jgp_kod, avg(w.srednia_wartosc_grupy) FROM (\n" +
"SELECT r.Kod, jgp.Rok , jgp.kod as jgp_kod, jgp.srednia_wartosc_grupy, (jgp.Liczba_pacjentow * r.Udzial)/100 as liczba_wystapien FROM health.rozpoznania_icd10 as r \n" +
"LEFT JOIN health.grupy_jgp as jgp \n" +
"ON r.Id_zestawienia = jgp.Id_zestawienia where r.Kod = '"+kodRozpoznania+"' and jgp.rok in ("+(rok-1)+","+rok+")\n" +
") as w where w.liczba_wystapien > 0.5 group by jgp_kod, w.Rok order by jgp_kod, Rok", (rs, rowNum)-> new pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.ZagregowaneSrednieWarotsciGrupByICD10KodByRok( rs.getInt(1),rs.getString(2), rs.getDouble(3)));
        //@formatter:on
    }
}