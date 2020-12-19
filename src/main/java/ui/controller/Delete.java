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
        Role[] authRoles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, authRoles);

        String destination = "RedirectController?command=ProfilePage";
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        String confirmation = request.getParameter("confirmation");

        if (!confirmation.isEmpty() && confirmation.equals("Delete")) {
            getService().deletePerson(person);
            getService().removeContacts(person.getUserid());
            getService().removeTestResults(person.getUserid());
            session.invalidate();
            //request.setAttribute("deleteMessage", "Your account has been removed");
            destination =  "RedirectController?command=DeleteSucces";
        }
        return destination;
    }
}