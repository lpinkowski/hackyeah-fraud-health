package pl.hackyeah.nhf.health.fraud.detector.icd10.domain;

import lombok.Getter;
import pl.hackyeah.nhf.health.fraud.detector.utils.CSVLine;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

@Getter
public class RecognitionRelativeIncrease implements CSVLine {

    public static final List<String> HEADER = asList("ICD10", "YEAR", "RELATIVE_INCREASE");

    private String ICD10;
    private int year;
    double relativeIncrease;

    public RecognitionRelativeIncrease(RecognitionWithJPGGroup actualGroup, double relativeIncrease) {
        this.ICD10 = actualGroup.getICD10();
        this.year = actualGroup.getYear();
        this.relativeIncrease = relativeIncrease;
    }

    public String asLine() {
        return format("%s;%d;%s", ICD10, year, relativeIncrease);
    }
}
