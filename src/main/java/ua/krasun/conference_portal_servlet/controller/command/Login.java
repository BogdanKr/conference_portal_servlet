package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class Login implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class);
    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        if (nonNull(request.getSession().getAttribute("userEmail"))) return "redirect:/";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null) return "/login.jsp";
        System.out.println("user enter email: "+ email + " " + password);

        System.out.println("entering DB : ");
        userService.findAllUsers().forEach(System.out::println);

        Optional<User> user = userService.findUser(email, password);
        if (!user.isPresent()) {
            logger.info("Invalid attempt of user email: '" + email + "'");
             request.setAttribute("error", true);
            return "/login.jsp";
        }
        if (CommandUtility.checkUserIsLogged(request, email)) {
            logger.info("User email " + email + " already logged.");
            throw new RuntimeException("You already logged");
        }
        logger.info("User email " + email + " logged successfully.");

        request.getSession().setAttribute("user", user.get());
        if (user.get().getRole().equals(Role.ADMIN)) {
            CommandUtility.setUserRole(request, Role.ADMIN, email);
            return "redirect:/conference/admin";
        } else if (user.get().getRole().equals(Role.USER)) {
            CommandUtility.setUserRole(request, Role.USER, email);
            return "redirect:/conference/user";
        } else {
            return "redirect:/index.jsp";
        }
    }
}