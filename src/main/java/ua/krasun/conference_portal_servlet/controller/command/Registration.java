package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null) return "/registration.jsp";
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        try {
            userService.addUser(email, password);
            logger.info("User email: " + email + " registration successfully.");
        } catch (WrongInputException e) {
            logger.warn("Entered email: " + email + " registration fail" + e);
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.invalid.input"));
            return "/registration.jsp";
        }
        request.setAttribute("success", true);
        request.setAttribute("message", bundle.getString("info.success.reg"));

        if (request.getSession().getAttribute("role") == null) return "/login.jsp";
        else return "/conference/admin/userlist";
    }
}
