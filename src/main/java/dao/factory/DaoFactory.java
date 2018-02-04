package dao.factory;

import dao.OrganizationDao;
import dao.TodoDao;
import dao.UserDao;
import dao.impl.OrganizationDaoImpl;
import dao.impl.TodoDaoImpl;
import dao.impl.UserDaoImpl;

public class DaoFactory {


    public static TodoDao getTodoDao() {
        return new TodoDaoImpl();
    }

    public static UserDao getUserDao() {return new UserDaoImpl();  }

    public static OrganizationDao getOrganizationDao() {return new OrganizationDaoImpl();  }

}
