package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserList implements Command {
    private UserService userService;

    public UserList(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("userList", userService.findAllUsers());
        return "/WEB-INF/admin/userlist.jsp";
    }
}
