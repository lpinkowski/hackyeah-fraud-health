package pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain;

import lombok.Getter;
import pl.hackyeah.nhf.health.fraud.oszusci.utils.CSVLine;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@Getter
public class RozpoznanieRelativeIncrease implements CSVLine {

    public static final List<String> HEADER = Arrays.asList("ICD10", "YEAR", "RELATIVE_INCREASE");
    private String ICD10;

    private int year;

    double relativeIncrease;

    public RozpoznanieRelativeIncrease(RozpoznanieWithJPGGroup actualGroup, double relativeIncrease) {
        this.ICD10 = actualGroup.getICD10();
        this.year = actualGroup.getYear();
        this.relativeIncrease = relativeIncrease;
    }

    public String asLine() {
        return format("%s;%d;%s", ICD10, year, relativeIncrease);
    }
}
