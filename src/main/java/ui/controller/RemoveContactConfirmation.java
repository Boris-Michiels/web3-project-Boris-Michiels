package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveContactConfirmation extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        String userid = person.getUserid();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Contact contact = getService().getOneContact(userid, firstName, lastName);
        request.setAttribute("contact", contact);
        return "removeContactConfirmation.jsp";
    }
}
