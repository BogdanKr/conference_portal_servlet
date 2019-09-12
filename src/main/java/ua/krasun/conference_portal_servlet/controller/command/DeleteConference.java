package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DeleteConference implements Command {
   private ConferenceService conferenceService;

    public DeleteConference(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String confId = request.getParameter("confId");
        if (Optional.ofNullable(confId).isEmpty()) {
            return "redirect:/conference/";
        }
        conferenceService.deleteConference(Long.parseLong(confId));
        request.setAttribute("success", true);
        request.setAttribute("message", "Conference deleted");
        return "conference/user/conferencelist";
    }
}
