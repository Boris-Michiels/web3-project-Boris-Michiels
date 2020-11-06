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
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person != null && !confirmation.isEmpty() && confirmation.equals("Remove")) {
            String userid = person.getUserid();
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            Contact contact = getService().getOneContact(userid, firstName, lastName);
            getService().removeOneContact(contact);
        }
        return "Controller?command=Contacts";
    }
}
