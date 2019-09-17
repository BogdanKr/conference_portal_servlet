package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class Login implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        ConferenceService conferenceService = new ConferenceService();
        if (nonNull(request.getSession().getAttribute("userEmail"))) return "redirect:/conference/logout";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null) return "/login.jsp";

        Optional<User> user = userService.findUser(email, password);
        if (user.isEmpty()) {
            logger.info("Invalid attempt of user email: '" + email + "'");
            request.setAttribute("error", true);
            request.setAttribute("message", "Invalid email or password");
            return "/login.jsp";
        }
        if (CommandUtility.checkUserIsLogged(request, email)) {
            logger.info("User email " + email + " already logged.");
            throw new RuntimeException("You already logged");
        }
        logger.info("User email " + email + " logged successfully.");

        CommandUtility.setUserInSession(user.get(), request);
        request.getSession().setAttribute("conferenceList", conferenceService.findAllConference(user.get().getId()));
        if (user.get().getRole().equals(Role.ADMIN)) {
            CommandUtility.setUserRole(request, Role.ADMIN, email);
            return "redirect:/conference/admin";
        }
        if (user.get().getRole().equals(Role.USER)) {
            CommandUtility.setUserRole(request, Role.USER, email);
            return "redirect:/conference/user";
        } else {
            CommandUtility.setUserRole(request, Role.SPEAKER, email);
            return "redirect:/conference/speaker";
        }
    }
}