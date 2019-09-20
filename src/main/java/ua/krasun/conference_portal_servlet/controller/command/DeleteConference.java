package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteConference implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteConference.class);
    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        String confId = request.getParameter("confId");
        if (Optional.ofNullable(confId).isEmpty()) {
            return "redirect:/conference/";
        }
        try {
            conferenceService.deleteConference(Long.parseLong(confId));
            request.setAttribute("success", true);
            request.setAttribute("message", bundle.getString("info.conf.deleted"));
            logger.info("Conference was deleted, ID: "+ confId);
        } catch (SQLException | NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.cant.delete.conf"));
        }
        return "conference/user/conferencelist";
    }
}
