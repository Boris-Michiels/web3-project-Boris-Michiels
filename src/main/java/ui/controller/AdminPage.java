package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdminPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN};
        Utility.checkRole(request, authRoles);

        List<Person> persons = getService().getAllPersons();
        List<Contact> contacts = getService().getAllContacts();
        List<TestResult> testResults = getService().getAllTestResults();
        Map<String, Person> personMap = persons.stream().collect(Collectors.toMap(Person::getUserid, Function.identity()));

        request.setAttribute("persons", persons);
        request.setAttribute("contacts", contacts);
        request.setAttribute("testResults", testResults);
        request.setAttribute("personMap", personMap);
        return "adminPage.jsp";
    }
}