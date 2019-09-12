package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.lang.Exception;
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
            User currentUser = (User) request.getSession().getAttribute("user");
            conferenceService.addConference(currentUser, LocalDate.parse(date), subject);
        } catch (Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", "Can't save ");

            return "/conference/admin";
        }
        request.setAttribute("success", true);
        request.setAttribute("message", "Conference added");
        return "/conference/admin";
    }
}
