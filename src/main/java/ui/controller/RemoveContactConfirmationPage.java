package ui.controller;

import domain.db.DbException;
import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveContactConfirmationPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.USER, Role.ADMIN};
        Utility.checkRole(request, authRoles);

        String destination = "RedirectController?command=ContactsPage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");

        try {
            int contactid = Integer.parseInt(request.getParameter("contactid"));
            Contact contact = getService().getOneContact(contactid);
            if (!contact.getUserid().equals(person.getUserid())) {
                authRoles = new Role[] {Role.ADMIN};
                Utility.checkRole(request, authRoles);
            }
            request.setAttribute("contact", contact);
            destination = "removeContactConfirmation.jsp";
        } catch (NumberFormatException | DbException ignored) {}
        return destination;
    }
}