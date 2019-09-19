package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.lang.Exception;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddConference implements Command {
    private static final Logger logger = LogManager.getLogger(AddConference.class);
    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        String date = request.getParameter("localDate");
        String subject = request.getParameter("subject");
        if (date == null) return "/conference/";

        try {
            if (!request.getParameter("conferenceEditId").isEmpty()) {
                String confId = request.getParameter("conferenceEditId");
                conferenceService.conferenceEdit(confId, LocalDate.parse(date), subject);
                logger.info("Conference: " + confId + "was edit");
                return "/conference/user/conferencelist";
            } else {
                User currentUser = (User) request.getSession().getAttribute("user");
                conferenceService.addConference(currentUser, LocalDate.parse(date), subject);
                logger.info("User: " + currentUser.getEmail() + "add new conference" + date + "/" + subject);
            }
        } catch (Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message",  bundle.getString("info.sorry.cant.add.conf"));
            return "/conference/admin";
        }

        request.getSession().setAttribute("conferenceList",
                conferenceService.findAllConference(((User) request.getSession().getAttribute("user")).getId()));
        request.setAttribute("success", true);
        request.setAttribute("message", bundle.getString("info.conference.added"));
        return "/conference/admin";
    }

}
