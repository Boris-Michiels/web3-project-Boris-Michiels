package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String confirmation = request.getParameter("confirmation");
        if (!confirmation.isEmpty() && confirmation.equals("Delete")) {
            HttpSession session = request.getSession();
            Person person = (Person) session.getAttribute("person");
            getService().deletePerson(person);
            getService().removeContacts(person.getUserid());
            request.setAttribute("deleteMessage", "Your account has been removed!");
            return "Controller?command=LogOut";
        }
        return "Controller?command=Profile";
    }
}