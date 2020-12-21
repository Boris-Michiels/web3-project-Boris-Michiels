package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;
import domain.model.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogIn extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] authRoles = {Role.GUEST};
        Utility.checkRole(request, authRoles);

        String destination = "Controller?command=ProfilePage";
        String userid = request.getParameter("useridLogIn");
        String password = request.getParameter("passwordLogIn");

        try {
            Person person = getService().getPerson(userid);
            boolean correct = person.isCorrectPassword(password);
            if (correct) {
                HttpSession session = request.getSession();
                session.setAttribute("person", person);
                destination =  "RedirectController?command=ProfilePage";
            } else throw new DomainException("Invalid password");
        } catch (DbException | DomainException e) {
            request.setAttribute("logInMessage", "No valid userid / password");
        }
        request.setAttribute("useridLogInPreviousValue", userid);
        return destination;
    }
}