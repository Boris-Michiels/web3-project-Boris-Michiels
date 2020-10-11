package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Remove extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String confirmation = request.getParameter("confirmation");
        if (!confirmation.isEmpty() && confirmation.equals("Remove")) {
            HttpSession session = request.getSession();
            Person person = (Person) session.getAttribute("person");
            String userid = person.getUserid();
            contactTracingService.deletePerson(userid);
            session.invalidate();
            request.setAttribute("removed", "Your account has been removed!");
        }
        return "profile.jsp";
    }
}