package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "Controller?command=Profile";
        String confirmation = request.getParameter("confirmation");
        if (!confirmation.isEmpty() && confirmation.equals("Delete")) {
            HttpSession session = request.getSession();
            Person person = (Person) session.getAttribute("person");
            String userid = person.getUserid();
            getService().deletePerson(person);
            getService().removeContacts(userid);
            request.setAttribute("message", "Your account has been removed!");
            destination = "Controller?command=LogOut";
        }
        return destination;
    }
}