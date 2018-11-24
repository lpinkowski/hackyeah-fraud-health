package pl.hackyeah.nhf.health.fraud.oszusci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.hackyeah.nhf.health.fraud.oszusci.domain.RozpoznanieWithJPGGroup;
import pl.hackyeah.nhf.health.fraud.oszusci.infrastructure.jdbc.Repo;
import pl.hackyeah.nhf.health.fraud.oszusci.infrastructure.jdbc.RozpoznaniaICD10Repository;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.lang.String.format;

@SpringBootApplication
@Component
public class OszusciApplication {

    private static Repo repo;

    @Autowired
    private RozpoznaniaICD10Repository rozpoznaniaICD10Repository;

    public static void main(String[] args) {
        SpringApplication.run(OszusciApplication.class, args);
        List<RozpoznanieWithJPGGroup> icd10WithJPGGroupByYears = repo.getICD10WithJPGGroupByYears();
        
        icd10WithJPGGroupByYears.forEach(OszusciApplication::output);

        System.out.println(format("Rows processed -> %d ",icd10WithJPGGroupByYears.size()));
    }

    @PostConstruct
    public void init() {
        repo = rozpoznaniaICD10Repository;
    }

    private static void output(RozpoznanieWithJPGGroup group) {
        System.out.println(format("ID: [%s] -  Year: [%d] - No. of cases: [%d]\n", group.getICD10(), group.getYear(), group.getNumberOfCases()));
    }
}
