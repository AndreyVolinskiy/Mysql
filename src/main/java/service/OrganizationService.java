package service;

import model.Organization;

import java.sql.Date;
import java.util.List;

public interface OrganizationService {


    List<Organization> getAll();

    Organization getById(long id);

    boolean create(Organization organization);

    boolean updateDate(Long id, Date newDate);

    boolean delete(Long id);
}
