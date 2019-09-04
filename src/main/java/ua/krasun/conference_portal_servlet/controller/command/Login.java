package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.nonNull;

public class Login implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " " + password);
        if (nonNull(request.getSession().getAttribute("userEmail"))) return "/welcome.jsp";
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            if (email != null) request.setAttribute("error", true);
            return "/login.jsp";
        } else {
            //todo: check login with DB
            if (CommandUtility.checkUserIsLogged(request, email)) {
                throw new RuntimeException("You already logged");
            }
            if (email.equals("a@a")) {
                CommandUtility.setUserRole(request, Role.ADMIN, email);
                return "/WEB-INF/admin/adminbasic.jsp";
            } else if (email.equals("u@u")) {
                CommandUtility.setUserRole(request, Role.USER, email);
                return "/WEB-INF/user/userbasic.jsp";
            } else {
                CommandUtility.setUserRole(request, Role.GUEST, email);
                return "/welcome.jsp";
            }
        }
    }
}
//  redirect:
