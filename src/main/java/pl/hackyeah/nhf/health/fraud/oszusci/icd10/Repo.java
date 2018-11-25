package pl.hackyeah.nhf.health.fraud.oszusci.icd10;

import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.AggregatedAverageValueGroupByICD10CodeYear;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RecognitionWithJPGGroup;

import java.util.List;

public interface Repo {
    List<RecognitionWithJPGGroup> getICD10WithJPGGroupByYearsOrderByCodeYearList();

    List<AggregatedAverageValueGroupByICD10CodeYear> getAggregatedAverageValueGroupByICD10CodeYearList(String recognitionCode, int year);
}
