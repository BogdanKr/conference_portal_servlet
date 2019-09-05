package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class);
    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null) return "/registration.jsp";
        User newUser = User.builder()
                .email(email)
                .password(password)
                .role(Role.USER)
                .build();
        userService.addUser(newUser);
        return "/registration.jsp";
    }
}
