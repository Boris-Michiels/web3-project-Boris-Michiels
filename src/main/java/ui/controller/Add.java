package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Add extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "Controller?command=Register";
        ArrayList<String> errors = new ArrayList<>();
        Person person = new Person();

        setUserid(person, request, errors);
        setEmail(person, request, errors);
        setPassword(person, request, errors);
        setFirstName(person, request, errors);
        setLastName(person, request, errors);

        if (errors.size() == 0) {
            try {
                getService().addPerson(person);
                removeAllAttributes(request);
                destination = "Controller?command=Home";
            } catch (DbException d) {
                errors.add(d.getMessage());
            }
        }
        if (errors.size() > 0) request.setAttribute("errors", errors);
        return destination;
    }

    private void setUserid(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String userid = request.getParameter("userid");
        try {
            person.setUserid(userid);
            request.setAttribute("useridPreviousValue", userid);
            request.setAttribute("useridClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("useridClass", "has-error");
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            person.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
            request.setAttribute("emailClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("emailClass", "has-error");
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            person.setPassword(password);
            request.setAttribute("passwordPreviousValue", password);
            request.setAttribute("passwordClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("passwordClass", "has-error");
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
            request.setAttribute("firstNameClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("firstNameClass", "has-error");
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            person.setLastName(request.getParameter("lastName"));
            request.setAttribute("lastNamePreviousValue", lastName);
            request.setAttribute("lastNameClass", "has-success");
        } catch (DomainException d) {
            errors.add(d.getMessage());
            request.setAttribute("lastNameClass", "has-error");
        }
    }

    private void removeAllAttributes(HttpServletRequest request) {
        List<String> attributeNames = Collections.list(request.getAttributeNames());
        for (String s : attributeNames) request.removeAttribute(s);
    }
}