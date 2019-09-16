package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserList implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        request.getSession().setAttribute("userList", userService.findAllUsers());
        return "/WEB-INF/admin/userlist.jsp";
    }
}
