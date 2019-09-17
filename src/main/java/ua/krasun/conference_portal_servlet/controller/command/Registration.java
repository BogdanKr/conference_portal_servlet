package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null) return "/registration.jsp";
        try {
            userService.addUser(email, password);
            logger.info("User email " + email + " registration successfully.");
        } catch (SQLException e) {
            request.setAttribute("error", true);
            return "/registration.jsp";
        }
        request.setAttribute("success", true);
        request.setAttribute("message", "Success registration");
        if (request.getSession().getAttribute("role") == null) return "/registration.jsp";
        else return "/conference/admin/userlist";
    }
}
