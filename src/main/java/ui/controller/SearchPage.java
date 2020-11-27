package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class SearchPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        String userid = person.getUserid();
        List<Contact> contacts;
        TestResult testResult = getService().getLatestTestResult(userid);
        contacts = getService().getContacts(userid);
        if (testResult == null) {
            request.setAttribute("searchMessage", "You have not registered a positive test yet");
        } else if (!contacts.isEmpty()) {
            LocalDate testResultDate = testResult.getDate();
            contacts.removeIf(contact -> contact.getTimeStamp().toLocalDate().isBefore(testResultDate));
            request.setAttribute("contacts", contacts);
        }
        if (contacts.isEmpty() && testResult != null) {
            request.setAttribute("searchMessage", "You haven't had any contacts since your last test");
        }
        return "search.jsp";
    }
}