package ui.controller;

import domain.model.Person;
import domain.model.Role;
import domain.model.TestResult;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TestResultPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);

        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        List<TestResult> testResults = getService().getTestResults(person.getUserid());

        request.setAttribute("testResults", testResults);
        return "testResult.jsp";
    }
}