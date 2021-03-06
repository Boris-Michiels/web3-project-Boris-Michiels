package ui.controller;

import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfilePage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.GUEST, Role.USER, Role.ADMIN};
        Utility.checkRole(request, authRoles);
        return "profile.jsp";
    }
}