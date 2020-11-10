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
        String userid = request.getParameter("useridLogIn");
        String password = request.getParameter("passwordLogIn");
        try {
            Person person = getService().getPerson(userid);
            request.setAttribute("useridLogInPreviousValue", userid);
            boolean correct;
            correct = person.isCorrectPassword(password);
            if (correct) {
                HttpSession session = request.getSession();
                session.setAttribute("person", person);
            } else request.setAttribute("logInMessage", "No valid userid/password");
        } catch (DbException | DomainException e) {
            request.setAttribute("logInMessage", e.getMessage());
        }
        return "Controller?command=Profile";
    }
}