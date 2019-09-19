package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class ConfRegistration implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        long userId = ((User) request.getSession().getAttribute("user")).getId();
        long confId = Long.parseLong(request.getParameter("confId"));
        conferenceService.registration(confId, userId);
        request.getSession().setAttribute("conferenceList",
                conferenceService.findAllConference(userId));
        return "redirect:" + request.getHeader("referer");
    }
}
