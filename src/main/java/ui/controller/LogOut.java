package ui.controller;

import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.USER, Role.ADMIN};
        Utility.checkRole(request, authRoles);

        String destination = "RedirectController?command=ProfilePage";
        HttpSession session = request.getSession();

        session.removeAttribute("person");
        session.setAttribute("statusMessage", "Your have been logged out");
        session.setAttribute("messageClass", "alert-success");
        return destination;
    }
}