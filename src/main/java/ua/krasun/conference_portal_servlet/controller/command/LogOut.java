package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("userEmail") == null) return "redirect:/";
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        request.getSession().invalidate();
        request.setAttribute("success", true);
        request.setAttribute("message", bundle.getString("info.logout"));
        return "/index.jsp";
    }
}
