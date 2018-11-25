package pl.hackyeah.nhf.health.fraud.oszusci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.ICD10Service;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.ICD10ServiceImpl;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.Fraud;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.domain.RozpoznanieRelativeIncrease;
import pl.hackyeah.nhf.health.fraud.oszusci.utils.CSVLine;
import pl.hackyeah.nhf.health.fraud.oszusci.utils.CSVUtils;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@SpringBootApplication
@Component
public class OszusciApplication {

    private static ICD10Service service;

    @Autowired
    private ICD10ServiceImpl icd10Service;

    @PostConstruct
    public void init() {
        service = icd10Service;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(OszusciApplication.class, args);

        System.out.println("Processing - Relative Increases");
        List<RozpoznanieRelativeIncrease> foundRelativeIncreases = service.findRelativeIncreaseYearToYear();
        foundRelativeIncreases.forEach(OszusciApplication::output);
        exportRelativeIncrease(foundRelativeIncreases);
        System.out.println(format("Rows processed -> %d ", foundRelativeIncreases.size()));

        System.out.println("Processing... - suspicious to confirmed");
        List<Fraud> fraudList = service.findFraud(foundRelativeIncreases);
        fraudList.forEach(OszusciApplication::output);
        exportFraud(fraudList);
        System.out.println(format("Rows processed -> %d ", fraudList.size()));
    }

    private static void exportFraud(List<Fraud> fraudList) throws IOException {
        List<String> csvData = fraudList.stream()
                .map(Fraud::asLine)
                .collect(Collectors.toList());
        exportDataToCSV(csvData, Fraud.HEADER, "Frauds");
    }

    private static void output(Fraud fraud) {
        System.out.println(format("ICD10: [%s] -  Year: [%d] - JGP_Code: [%s] \n", fraud.getIcd10(), fraud.getYear(), fraud.getJgpCode()));
    }

    private static void output(RozpoznanieRelativeIncrease g) {
        System.out.println(format("ID: [%s] -  Year: [%d] - Relative Increses: [%s] \n", g.getICD10(), g.getYear(), g.getRelativeIncrease()));
    }

    private static void exportRelativeIncrease(final List<RozpoznanieRelativeIncrease> data) throws IOException {
        List<String> csvData = data.stream()
                .map(CSVLine::asLine)
                .collect(Collectors.toList());
        exportDataToCSV(csvData, RozpoznanieRelativeIncrease.HEADER, "RelativeIncrease");
    }

    private static void exportDataToCSV(final List<String> csvData, final List<String> header, String fileName) throws IOException {
        String csvFile = fileName + ".csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            CSVUtils.writeLine(writer, header, ';');
            CSVUtils.writeLine(writer, csvData, '\n');
        }
    }
}
