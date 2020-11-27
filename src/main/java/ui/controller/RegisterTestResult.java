package ui.controller;

import domain.db.DbException;
import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class RegisterTestResult extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);
        String destination = "Controller?command=RegisterTestResultPage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        ArrayList<String> errors = new ArrayList();
        TestResult testResult = new TestResult();

        setUserid(person, testResult, request, errors);
        setDate(testResult, request, errors);

        if (errors.size() == 0) {
            try {
                getService().addTestResult(testResult);
                destination = "RedirectController?command=ContactsPage";
            } catch (DbException d) {
                errors.add(d.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return destination;
    }

    private void setUserid(Person person, TestResult testResult, HttpServletRequest request, ArrayList<String> errors) {
        String userid = person.getUserid();
        try {
            testResult.setUserid(userid);
        } catch (DomainException d) {
            errors.add(d.getMessage());
        }
    }

    private void setDate(TestResult testResult, HttpServletRequest request, ArrayList<String> errors) {
        String date = request.getParameter("date");
        try {
            testResult.setDateString(date);
            request.setAttribute("datePreviousValue", date);
            request.setAttribute("dateClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("dateClass", "has-error");
        }
    }
}