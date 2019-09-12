package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddConference implements Command {
    private ConferenceService conferenceService;

    public AddConference(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String date = request.getParameter("localDate");
        String subject = request.getParameter("subject");
        if (date == null) return "/";
        try {
            conferenceService.addConference(LocalDate.parse(date), subject);
        } catch (SQLException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", "Can't save ");

            return "/";
        }
        request.setAttribute("success", true);
        request.setAttribute("message", "Conference added");
        return "/";
    }
}
