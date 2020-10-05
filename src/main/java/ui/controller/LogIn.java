package ui.controller;

import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogIn extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = contactTracingService.getPerson(request.getParameter("userid"));
        if (person != null) {
            try {
                if (person.isCorrectPassword(request.getParameter("password"))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("person", person);
                }
            } catch (DomainException ignored) {
            }
        }
        request.setAttribute("loginfail", "No valid userid/password");
        return "index.jsp";
    }
}