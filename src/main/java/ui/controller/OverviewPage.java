package ui.controller;

import domain.model.Person;
import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OverviewPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN};
        Utility.checkRole(request, authRoles);
        List<Person> persons = getService().getAllPersons();
        request.setAttribute("persons", persons);
        return "personOverview.jsp";
    }
}