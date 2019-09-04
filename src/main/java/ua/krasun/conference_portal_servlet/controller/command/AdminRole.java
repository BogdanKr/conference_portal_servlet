package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminRole implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String role =  request.getSession().getAttribute("role").toString();
        if (role != null && role.equals("ADMIN"))
            return "/WEB-INF/admin/adminbasic.jsp";
        else return "redirect:/";
    }
}
