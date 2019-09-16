package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

public class DeleteConference implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        String confId = request.getParameter("confId");
        if (Optional.ofNullable(confId).isEmpty()) {
            return "redirect:/conference/";
        }
        try {
            conferenceService.deleteConference(Long.parseLong(confId));
            request.setAttribute("success", true);
            request.setAttribute("message", "Conference deleted");
        } catch (SQLException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", "Sorry, can't delete conference. Please check presentations");
        }
        return "conference/user/conferencelist";
    }
}
