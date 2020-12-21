package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.USER, Role.ADMIN};
        Utility.checkRole(request, authRoles);

        String destination = "RedirectController?command=ProfilePage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        String newPassword = request.getParameter("newPassword");

        try {
            person.setPassword(newPassword);
            getService().updatePerson(person);
            session.setAttribute("statusMessage", "Your password has been updated");
            session.setAttribute("messageClass", "alert-success");
        } catch (DomainException e) {
            session.setAttribute("statusMessage", e.getMessage());
            session.setAttribute("messageClass", "alert-danger");
        }
        return destination;
    }
}