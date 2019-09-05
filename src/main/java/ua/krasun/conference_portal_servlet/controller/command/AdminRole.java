package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

public class AdminRole implements Command {
    @Override
    public String execute(HttpServletRequest request) {
//        Role role = (Role) request.getSession().getAttribute("role");
//        if (role != null && role.equals(Role.ADMIN))
            return "/WEB-INF/admin/adminbasic.jsp";
//        else return "redirect:/";
    }
}
