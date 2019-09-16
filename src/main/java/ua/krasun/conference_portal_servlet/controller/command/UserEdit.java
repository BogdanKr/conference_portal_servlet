package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class UserEdit implements Command {
    private static final Logger logger = LogManager.getLogger(AdminEdit.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";
        if (Optional.ofNullable(request.getParameter("userId")).isEmpty()) {
            request.setAttribute("user", user);
            return "/WEB-INF/user/useredit.jsp";
        }

        String id = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            userService.userEditIfNotAdmin(id, firstName, email, password);
            request.setAttribute("success", true);
            request.setAttribute("message", "Success Save");
        } catch (SQLException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", "Invalid input");
        }

        request.setAttribute("user", userService.findUserById(user.getId()).orElse(new User()));
        return "/WEB-INF/user/useredit.jsp";
    }
}
