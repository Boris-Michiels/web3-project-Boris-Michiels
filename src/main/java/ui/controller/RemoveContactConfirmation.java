package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveContactConfirmation extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        Contact contact = getService().getOneContact(userid, firstName, lastName, date, time);
        request.setAttribute("contact", contact);
        return "removeContactConfirmation.jsp";
    }
}
