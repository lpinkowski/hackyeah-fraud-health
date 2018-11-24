package pl.hackyeah.nhf.health.fraud.oszusci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.ICD10Service;
import pl.hackyeah.nhf.health.fraud.oszusci.icd10.ICD10ServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.lang.String.format;

@SpringBootApplication
@Component
public class OszusciApplication {

    private static ICD10Service service;

    @Autowired
    private ICD10ServiceImpl icd10Service;

    public static void main(String[] args) {
        SpringApplication.run(OszusciApplication.class, args);
        List<ICD10ServiceImpl.RozpoznanieRelativeIncrease> rozpoznanieRelativeIncreases = service.przyrostyRozpoznanRokDoRoku();

        rozpoznanieRelativeIncreases.forEach(OszusciApplication::output);

        System.out.println(format("Rows processed -> %d ", rozpoznanieRelativeIncreases.size()));
    }

    @PostConstruct
    public void init() {
        service = icd10Service;
    }

    private static void output(ICD10ServiceImpl.RozpoznanieRelativeIncrease g) {
        System.out.println(format("ID: [%s] -  Year: [%d] - Relative Increses: [%s] \n", g.getICD10(), g.getYear(), g.getRelativeIncrease()));
    }
}
