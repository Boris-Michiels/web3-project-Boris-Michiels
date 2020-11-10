package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String confirmation = request.getParameter("confirmation");
        if (!confirmation.isEmpty() && confirmation.equals("Remove")) {
            int contactid = Integer.parseInt(request.getParameter("contactid"));
            Contact contact = getService().getOneContact(contactid);
            getService().removeOneContact(contact);
            request.setAttribute("contactRemovedMessage", "Contact has been removed");
        }
        return "Controller?command=Contacts";
    }
}
