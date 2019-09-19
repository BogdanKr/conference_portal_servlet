package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminEdit implements Command {
    private static final Logger logger = LogManager.getLogger(AdminEdit.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        String id = request.getParameter("id");

        if (Optional.ofNullable(request.getParameter("userId")).isEmpty()) {
            try {
                Optional<User> user = userService.findUserById(Integer.parseInt(id));
                request.setAttribute("user", user.orElseThrow(SQLException::new));
                request.setAttribute("roles", userService.getRoles());
                return "/WEB-INF/admin/adminedit.jsp";
            } catch (SQLException | NumberFormatException e) {
                logger.info("Invalid no such user ID '" + id + "'");
                request.setAttribute("error", true);
                request.setAttribute("message", bundle.getString("info.invalid.input"));
                return "/conference/admin/userlist";
            }
        }

        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String active = request.getParameter("active");
        String role = request.getParameter("role");

        try {
            userService.userEdit(id, firstName, email, password, active, role);
            request.setAttribute("success", true);
            request.setAttribute("message", "Success Save");
        } catch (SQLException | NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.invalid.input"));
        }
        return "/conference/admin/userlist";
    }
}
