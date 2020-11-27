package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN};
        Utility.checkRole(request, authRoles);
        List<Person> persons = getService().getAllPersons();
        List<Contact> contacts = getService().getAllContacts();
        List<TestResult> testResults = getService().getAllTestResults();
        request.setAttribute("persons", persons);
        request.setAttribute("contacts", contacts);
        request.setAttribute("testResults", testResults);
        return "adminPage.jsp";
    }
}