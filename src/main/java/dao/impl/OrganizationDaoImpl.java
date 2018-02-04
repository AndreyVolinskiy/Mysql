package dao.impl;

import dao.OrganizationDao;
import data.Database;
import model.Organization;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrganizationDaoImpl implements OrganizationDao {

    private static final String SELECT = "SELECT * FROM organizations";
    private static final String GET_BY_ID = "SELECT * FROM organizations WHERE id=?";
    private static final String CREATE = "INSERT INTO organizations (name, placement, date) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE organizations SET date=? WHERE id=?";
    private static final String DELETE = "DELETE FROM organizations WHERE id=?";

    @Override
    public List<Organization> getAll() {

        LinkedList<Organization> list = new LinkedList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Organization organization = new Organization();
                organization.setId(resultSet.getLong("id"));
                organization.setName(resultSet.getString("name"));
                organization.setPlacement(resultSet.getString("placement"));
                organization.setDate(resultSet.getDate("date"));
                list.add(organization);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Organization getById(long id) {

        Organization organization = new Organization();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            organization.setId(resultSet.getLong("id"));
            organization.setName(resultSet.getString("name"));
            organization.setPlacement(resultSet.getString("placement"));
            organization.setDate(resultSet.getDate("date"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organization;
    }

    @Override
    public boolean create(Organization organization) {

        try( Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(CREATE)) {

            statement.setString(1, organization.getName());
            statement.setString(2, organization.getPlacement());
            statement.setDate(3,organization.getDate());

            if (statement.executeUpdate() != 0) {
                System.out.println("Organization created.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error...");
        return false;
    }

    @Override
    public boolean updateDate(Long id, Date newDate) {

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setDate(1, newDate);
            statement.setLong(2, id);

            if (statement.executeUpdate() != 0) {
                System.out.println("DB updated by date.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error detected");
        return false;
    }

    @Override
    public boolean delete(Long id) {

        try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);
            if (statement.executeUpdate() != 0) {
                System.out.println("Organization deleted.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error occurred.");
        return false;
    }
}
