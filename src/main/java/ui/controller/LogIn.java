package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogIn extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = contactTracingService.getPerson(request.getParameter("userid"));
        boolean correct = false;
        String password = request.getParameter("password");

        if (person != null && !password.isEmpty()) {
            correct = person.isCorrectPassword(request.getParameter("password"));
        }
        if (person != null && correct) {
            HttpSession session = request.getSession();
            session.setAttribute("person", person);
        } else request.setAttribute("loginfail", "No valid userid/password");
        return "index.jsp";
    }
}