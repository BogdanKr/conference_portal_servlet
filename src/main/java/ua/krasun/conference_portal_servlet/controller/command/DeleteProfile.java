package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteProfile implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteProfile.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        String deleteId = request.getParameter("deleteId");
        Optional<String> locale = Optional.ofNullable((String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        if (Optional.ofNullable(deleteId).isEmpty()) {
            return "/conference/";
        }
        User currentUser = (User) request.getSession().getAttribute("user");
        try {
            if (currentUser.getId() == Long.parseLong(deleteId) || currentUser.getRole().equals(Role.ADMIN))
                userService.deleteUser(Long.parseLong(deleteId));
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.profile.deleted"));
            logger.info("User: " + currentUser.getEmail() + " delete profile");
        } catch (WrongInputException | NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.cant.delete"));
            if (!request.getSession().getAttribute("role").equals(Role.ADMIN)) {
                return "/conference/user/edit";
            }
        }

        if (request.getSession().getAttribute("role").equals(Role.ADMIN)) {
            return "/conference/admin/userlist";
        }
        request.getSession().invalidate();
        return "/conference/";
    }
}
