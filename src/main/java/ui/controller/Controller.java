package ui.controller;

import domain.model.NotAuthorizedException;
import domain.service.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private ContactTracingService contactTracingService = new ContactTracingService();
    private HandlerFactory handlerFactory = new HandlerFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command"), destination = "RedirectController?command=HomePage";

        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, contactTracingService);
                destination = handler.handleRequest(request, response);
            } catch (NotAuthorizedException a) {
                request.getSession().setAttribute("statusMessage", a.getMessage());
                request.getSession().setAttribute("messageClass", "alert-danger");
            } catch (Exception exc) {
                System.out.println(exc);
            }
        }
        if (destination.startsWith("Redirect")) {
            response.sendRedirect(destination.replaceFirst("Redirect", ""));
        } else request.getRequestDispatcher(destination).forward(request, response);
    }
}