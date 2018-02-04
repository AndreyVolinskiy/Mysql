package start;

import model.Organization;
import model.Todo;
import model.User;
import service.OrganizationService;
import service.TodoService;
import service.UserService;
import service.factory.ServiceFactory;
import util.TodoStatus;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Demo {

    public static void main(String[] args) throws SQLException {

        UserService userService = ServiceFactory.getUserService();
        User user = new User();
        user.setName("Alex");

        userService.save(user);
        userService.update("Petya", 1L);
        userService.delete(4L);
        System.out.println(userService.findById(2L));
        System.out.println(userService.findAll());


        TodoService todoService = ServiceFactory.getTodoService();
        Todo todo = new Todo();
        todo.setName("Entertainment");
        todo.setStatus(TodoStatus.HIGH);

        System.out.println(todoService.save(todo));
        System.out.println(todoService.update(TodoStatus.LOW, 5L));
        System.out.println(todoService.delete(9L));
        System.out.println(todoService.findById(10L));
        System.out.println(todoService.findAll());


        OrganizationService organizationService = ServiceFactory.getOrganizationService();
        Organization organization = new Organization();
        organization.setName("Miracle IT");
        organization.setPlacement("Kiev");
        organization.setDate(Date.valueOf(LocalDate.of(2000,2,6)));
        Date newDate = Date.valueOf(LocalDate.of(2018,01,19));

        System.out.println(organizationService.create(organization));
        System.out.println(organizationService.updateDate(5L, newDate));
        System.out.println(organizationService.delete(6L));
        System.out.println(organizationService.getById(3L));
        System.out.println(organizationService.getAll());
    }
}
