package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AdminEdit implements Command {
    private static final Logger logger = LogManager.getLogger(AdminEdit.class);
    private UserService userService;

    public AdminEdit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        try {
            int userId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            request.setAttribute("error", true);
            return "/WEB-INF/admin/userlist.jsp";
        }

        if (!Optional.ofNullable(request.getParameter("userId")).isPresent()) {
            System.out.println();
            Optional<User> user = userService.findUserById(Integer.parseInt(id));
            if (!user.isPresent()) {
                logger.info("Invalid no such user ID '" + id + "'");
                request.setAttribute("error", true);
                request.setAttribute("message", "Invalid input");
                return "/WEB-INF/admin/userlist.jsp";
            }
            request.setAttribute("user", user.get());
            request.setAttribute("roles", userService.getRoles());
            return "/WEB-INF/admin/adminedit.jsp";
        }

        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String active = request.getParameter("active");
        String role = request.getParameter("role");

        userService.userEdit(id, firstName, email, password, active, role);
        return "redirect:/conference/admin/userlist";
    }
}
