package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AddPresentation implements Command {
    PresentationService presentationService;
    ConferenceService conferenceService;

    public AddPresentation(PresentationService presentationService, ConferenceService conferenceService) {
        this.presentationService = presentationService;
        this.conferenceService = conferenceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String confId = request.getParameter("conf");
        if (confId != null) {
            Conference conference;
            try {
                conference = conferenceService.findById(Integer.parseInt(confId))
                        .orElseThrow(() -> new WrongInputException("No such Conference"));
            } catch (java.lang.Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("message", "Can't add presentation ");
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
            request.getSession().setAttribute("conferenceList", conferenceService.findAllConference());
            return "/conference/speaker";
        } catch (SQLException | WrongInputException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", "Can't add presentation ");
            return "/conference/speaker";
        }
    }
}
