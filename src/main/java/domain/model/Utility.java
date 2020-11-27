package domain.model;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static void checkRole(HttpServletRequest request, Role[] roles) {
        Person person = (Person) request.getSession().getAttribute("person");
        if (person == null) throw new NotAuthorizedException("You need to be logged in to request this page");
        Role role = person.getRole();
        for (Role authRole : roles) {
            if (role == authRole) return;
        }
        throw new NotAuthorizedException("You have insufficient rights to request this page");
    }
}