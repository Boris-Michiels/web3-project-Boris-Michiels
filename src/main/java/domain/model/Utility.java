package domain.model;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static void checkRole(HttpServletRequest request, Role[] roles) {
        Role role = Role.GUEST;
        Person person = (Person) request.getSession().getAttribute("person");
        if (person != null) role = person.getRole();
        for (Role authRole : roles) {
            if (role == authRole) return;
        }
        throw new NotAuthorizedException("You are not authorized to perform this action");
    }
}