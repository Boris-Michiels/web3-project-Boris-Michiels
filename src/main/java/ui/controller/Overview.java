package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons = contactTracingService.getPersons();
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
    }
}