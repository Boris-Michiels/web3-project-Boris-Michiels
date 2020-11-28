package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveContactConfirmationPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);
        String destination = "removeContactConfirmation.jsp";
        try {
            int contactid = Integer.parseInt(request.getParameter("contactid"));
            Contact contact = getService().getOneContact(contactid);
            request.setAttribute("contact", contact);
        } catch (NumberFormatException | DbException e) {
            destination = "Controller?command=ContactsPage";
        }
        return destination;
    }
}