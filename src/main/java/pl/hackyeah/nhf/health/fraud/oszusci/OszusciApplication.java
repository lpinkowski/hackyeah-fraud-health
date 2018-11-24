package pl.hackyeah.nhf.health.fraud.oszusci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.hackyeah.nhf.health.fraud.oszusci.infrastructure.jdbc.RozpoznaniaICD10Repository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Component
public class OszusciApplication {

	@Autowired
	RozpoznaniaICD10Repository rozpoznaniaICD10Repository;

	public static void main(String[] args) {
		SpringApplication.run(OszusciApplication.class, args);
	}

	@PostConstruct
	public void init() {

	}
}
