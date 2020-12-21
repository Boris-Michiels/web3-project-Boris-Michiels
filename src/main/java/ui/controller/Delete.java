package ui.controller;

import domain.model.Person;
import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.USER, Role.ADMIN};
        Utility.checkRole(request, authRoles);

        String destination = "RedirectController?command=ProfilePage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        String confirmation = request.getParameter("confirmation");

        if (confirmation != null && confirmation.equals("Delete")) {
            getService().deletePerson(person);
            getService().removeContacts(person.getUserid());
            getService().removeTestResults(person.getUserid());
            session.removeAttribute("person");
            session.setAttribute("statusMessage", "Your account has been removed");
            session.setAttribute("messageClass", "alert-success");
        }
        return destination;
    }
}