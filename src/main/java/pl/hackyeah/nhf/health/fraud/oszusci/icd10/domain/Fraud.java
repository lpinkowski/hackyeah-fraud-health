package pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain;

import lombok.Getter;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

@Getter
public class Fraud {
    public static List<String> HEADER = asList("ICD10", "YEAR", "JGP_CODE", "ACTUAL_GROWTH", "RELATIVE_INCREASE");
    private String jgpCode;
    private String icd10;
    private int year;
    private double actualGrowth;
    double relativeIncrease;

    public Fraud(String jgpCode, String icd10, int year, double actualGrowth, double relativeIncrease) {
        this.jgpCode = jgpCode;
        this.icd10 = icd10;
        this.year = year;
        this.actualGrowth = actualGrowth;
        this.relativeIncrease = relativeIncrease;
    }

    public String asLine() {
        return format("%s;%d;%s;%s;%s", icd10, year, jgpCode, actualGrowth, relativeIncrease);
    }
}
