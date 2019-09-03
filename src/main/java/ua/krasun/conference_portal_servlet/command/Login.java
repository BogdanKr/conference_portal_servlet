package ua.krasun.conference_portal_servlet.command;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email +" "+password);
        if ( email==null || email.isEmpty() || password==null || password.isEmpty() ) return "/login.jsp";
        else return "redirect:/welcome.jsp";
    }
}
