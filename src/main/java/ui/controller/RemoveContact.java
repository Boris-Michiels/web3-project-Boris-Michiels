package ui.controller;

import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);
        String destination = "RedirectController?command=ContactsPage";
        String confirmation = request.getParameter("confirmation");

        if (!confirmation.isEmpty() && confirmation.equals("Remove")) {
            int contactid = Integer.parseInt(request.getParameter("contactid"));
            getService().removeOneContact(contactid);
            //request.setAttribute("contactRemovedMessage", "Contact has been removed");
            destination = "RedirectController?command=RemoveContactSucces";
        }
        return destination;
    }
}