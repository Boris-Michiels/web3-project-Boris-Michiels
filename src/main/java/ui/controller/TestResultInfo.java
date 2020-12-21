package ui.controller;

import domain.db.DbException;
import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class TestResultInfo extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.USER, Role.ADMIN};
        Utility.checkRole(request, authRoles);

        String destination = "RedirectController?command=TestResultPage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        String userid = person.getUserid();
        List<Contact> testResultContacts = getService().getContacts(userid);

        try {
            int testResultid = Integer.parseInt(request.getParameter("testResultid"));
            TestResult testResult = getService().getOneTestResult(testResultid);
            if (!testResult.getUserid().equals(person.getUserid())) return destination;
            request.setAttribute("testResult", testResult);
            if (!testResultContacts.isEmpty()) {
                LocalDate testResultDate = testResult.getDate();
                testResultContacts.removeIf(contact -> contact.getTimeStamp().toLocalDate().isBefore(testResultDate));
                request.setAttribute("testResultContacts", testResultContacts);
            }
            if (testResultContacts.isEmpty()) {
                request.setAttribute("testResultContactMessage", "You haven't had any contacts since this test");
            }
            destination = "Controller?command=SearchPage";
        } catch (NumberFormatException | DbException ignored) {}
        return destination;
    }
}