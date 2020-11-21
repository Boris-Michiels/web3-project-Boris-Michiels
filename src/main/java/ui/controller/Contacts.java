package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Contacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person != null) {
            List<Contact> contacts;
            if (person.getRole().equals("admin")) contacts = getService().getAllContacts();
            else contacts = getService().getContacts(person.getUserid());
            request.setAttribute("contacts", contacts);
            return "contacts.jsp";
        }
        throw new RuntimeException("You need to be logged in to view this page");
    }
}