package ui.controller;

import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteSucces extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.GUEST};
        Utility.checkRole(request, authRoles);

        request.setAttribute("deleteMessage", "Your account has been removed");
        return "Controller?command=ProfilePage";
    }
}