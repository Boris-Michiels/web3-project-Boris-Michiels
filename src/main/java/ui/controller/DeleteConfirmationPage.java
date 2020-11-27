package ui.controller;

import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteConfirmationPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);
        return "deleteConfirmation.jsp";
    }
}