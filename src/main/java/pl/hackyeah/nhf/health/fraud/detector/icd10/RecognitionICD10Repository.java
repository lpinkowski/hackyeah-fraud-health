package pl.hackyeah.nhf.health.fraud.detector.icd10;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.AggregatedAverageValueGroupByICD10CodeYear;
import pl.hackyeah.nhf.health.fraud.detector.icd10.domain.RecognitionWithJPGGroup;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecognitionICD10Repository implements Repo {

    private final JdbcTemplate jdbcTemplate;

    public List<RecognitionWithJPGGroup> getICD10WithJPGGroupByYearsOrderByCodeYearList() {
        //@formatter:off
        return jdbcTemplate.query(
                "SELECT w.Kod, w.Rok, sum(w.liczba_wystapien) " + 
                    "FROM(" +
                        "SELECT r.Kod, jgp.Rok, (jgp.Liczba_pacjentow * r.Udzial)/100 as liczba_wystapien " +
                         "FROM health.rozpoznania_icd10 as r " +
                         "LEFT JOIN health.grupy_jgp as jgp " +
                         "ON r.Id_zestawienia = jgp.Id_zestawienia) as w " +
                    "GROUP BY w.Kod, w.Rok order by w.Kod, w.Rok ", (rs, rowNum)-> new RecognitionWithJPGGroup(rs.getString(1), rs.getInt(2), (int)rs.getDouble(3)));
        //@formatter:on
    }

    public List<AggregatedAverageValueGroupByICD10CodeYear> getAggregatedAverageValueGroupByICD10CodeYearList(String recognitionCode, int year) {
        //@formatter:off
        return jdbcTemplate.query(
                "SELECT w.Rok, jgp_kod, avg(w.srednia_wartosc_grupy) FROM (\n" +
"SELECT r.Kod, jgp.Rok , jgp.kod as jgp_kod, jgp.srednia_wartosc_grupy, (jgp.Liczba_pacjentow * r.Udzial)/100 as liczba_wystapien FROM health.rozpoznania_icd10 as r \n" +
"LEFT JOIN health.grupy_jgp as jgp \n" +
"ON r.Id_zestawienia = jgp.Id_zestawienia where r.Kod = '"+recognitionCode+"' and jgp.rok in ("+(year-1)+","+year+")\n" +
") as w where w.liczba_wystapien > 0.5 group by jgp_kod, w.Rok order by jgp_kod, Rok", (rs, rowNum)-> new AggregatedAverageValueGroupByICD10CodeYear( rs.getInt(1),rs.getString(2), rs.getDouble(3)));
        //@formatter:on
    }
}