package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);

        String destination = "Controller?command=ProfilePage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        String newPassword = request.getParameter("newPassword");

        try {
            person.setPassword(newPassword);
            getService().updatePerson(person);
            //request.setAttribute("newPwMessage", "Your password has been updated");
            destination = "RedirectController?command=ChangePasswordSucces";
        } catch (DomainException e) {
            request.setAttribute("newPwMessage", e.getMessage());
        }
        return destination;
    }
}