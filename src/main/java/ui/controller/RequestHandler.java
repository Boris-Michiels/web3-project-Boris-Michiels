package ui.controller;

import domain.model.ContactTracingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected ContactTracingService contactTracingService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    public void setModel(ContactTracingService contactTracingServiceService) {
        this.contactTracingService = contactTracingServiceService;
    }

    public ContactTracingService getService() {
        return this.contactTracingService;
    }
}