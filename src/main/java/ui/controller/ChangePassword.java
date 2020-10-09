package ui.controller;

import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String newPassword = request.getParameter("newPassword");

        try {
            HttpSession session = request.getSession();
            Person person = (Person) session.getAttribute("person");
            person.setPassword(newPassword);
            request.setAttribute("newPMessage", "Your password has been updated");
        } catch (DomainException e) {
            request.setAttribute("newPMessage", e.getMessage());
        }
        return "profile.jsp";
    }
}