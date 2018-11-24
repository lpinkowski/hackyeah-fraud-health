package pl.hackyeah.nhf.health.fraud.oszusci.infrastructure.jdbc;

import pl.hackyeah.nhf.health.fraud.oszusci.domain.RozpoznanieWithJPGGroup;

import java.util.List;

public interface Repo {
    List<RozpoznanieWithJPGGroup> getICD10WithJPGGroupByYears();
}
