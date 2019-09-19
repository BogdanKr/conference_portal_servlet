package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.lang.Exception;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPresentation implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        PresentationService presentationService = new PresentationService();
        ConferenceService conferenceService = new ConferenceService();
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        String confId = request.getParameter("conf");
        if (confId != null) {
            Conference conference;
            try {
                conference = conferenceService.findById(Integer.parseInt(confId))
                        .orElseThrow(() -> new WrongInputException("No such Conference"));
            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("message", bundle.getString("info.cant.add.presentation"));
                return "/conference/";
            }
            request.setAttribute("conference", conference);
            return "/WEB-INF/speaker/addpresentation.jsp";
        }

        String theme = request.getParameter("theme");
        confId = request.getParameter("presentationConfId");
        User currentUser = (User) request.getSession().getAttribute("user");
        try {
            Conference conference = conferenceService.findById(Integer.parseInt(confId))
                    .orElseThrow(() -> new WrongInputException("No such Conference"));
            presentationService.addPresentation(theme, currentUser, conference);
            request.getSession().setAttribute("conferenceList", conferenceService.findAllConference(currentUser.getId()));
            return "redirect:/conference/speaker";
        } catch (SQLException | WrongInputException | NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.cant.add.presentation"));
            return "redirect:/conference/speaker";
        }
    }
}
