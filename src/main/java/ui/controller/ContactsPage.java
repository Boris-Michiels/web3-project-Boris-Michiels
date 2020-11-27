package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ContactsPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        List<Contact> contacts;

        if (person.getRole() == Role.ADMIN) contacts = getService().getAllContacts();
        else contacts = getService().getContacts(person.getUserid());
        request.setAttribute("contacts", contacts);
        return "contacts.jsp";
    }
}