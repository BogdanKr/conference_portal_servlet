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

public class UserEdit implements Command {
    private static final Logger logger = LogManager.getLogger(UserEdit.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/conference/";
        Optional<String> locale = Optional.ofNullable((String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        if (Optional.ofNullable(request.getParameter("userId")).isEmpty()) {
            request.setAttribute("user", user);
            if (request.getSession().getAttribute("role").equals(Role.SPEAKER))
                return "/WEB-INF/speaker/speakeredit.jsp";
            else
                return "/WEB-INF/user/useredit.jsp";
        }

        String id = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User newUser = userService.userEditIfNotAdmin(Integer.parseInt(id), firstName, email, password);
            CommandUtility.setUserInSession(newUser, request);
            request.setAttribute("success", true);
            request.setAttribute("message", bundle.getString("info.success.save"));
            request.setAttribute("user", newUser);
            logger.info("User " + user.getEmail() + "update profile");
        } catch (WrongInputException | NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.invalid.input"));
            request.setAttribute("user", user);
            logger.info("update failure" + e.getMessage());
        }

        if (request.getSession().getAttribute("role").equals(Role.SPEAKER))
            return "/WEB-INF/speaker/speakeredit.jsp";
        else
            return "/WEB-INF/user/useredit.jsp";
    }
}
