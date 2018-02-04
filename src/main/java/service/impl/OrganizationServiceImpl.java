package service.impl;

import dao.factory.DaoFactory;
import model.Organization;
import service.OrganizationService;

import java.sql.Date;
import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public List<Organization> getAll() {
        return DaoFactory.getOrganizationDao().getAll();
    }

    @Override
    public Organization getById(long id) {
        return DaoFactory.getOrganizationDao().getById(id);
    }

    @Override
    public boolean create(Organization organization) {
        return DaoFactory.getOrganizationDao().create(organization);
    }

    @Override
    public boolean updateDate(Long id, Date newDate) {
        return DaoFactory.getOrganizationDao().updateDate(id, newDate);
    }

    @Override
    public boolean delete(Long id) {
        return DaoFactory.getOrganizationDao().delete(id);
    }
}
