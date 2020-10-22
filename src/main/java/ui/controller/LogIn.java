package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        try {
            Person person = getService().getPerson(userid);
            request.setAttribute("useridPreviousValue", userid);
            boolean correct = false;
            correct = person.isCorrectPassword(password);

            if (correct) {
                HttpSession session = request.getSession();
                session.setAttribute("person", person);
            } else request.setAttribute("message", "No valid userid/password");
        } catch (DbException | DomainException e) {
            request.setAttribute("message", e.getMessage());
        }
        return "profile.jsp";
    }
}