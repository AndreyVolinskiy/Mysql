package service.factory;

import service.OrganizationService;
import service.TodoService;
import service.UserService;
import service.impl.OrganizationServiceImpl;
import service.impl.TodoServiceImpl;
import service.impl.UserServiceImpl;

/**
 * @author Igor Hnes on 2/2/18.
 */
public class ServiceFactory {


    public static TodoService getTodoService() {return new TodoServiceImpl(); }
    public static UserService getUserService() {return new UserServiceImpl(); }
    public static OrganizationService getOrganizationService() {return new OrganizationServiceImpl(); }

}
