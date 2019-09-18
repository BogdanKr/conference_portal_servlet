package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class AdminRole implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        request.getSession().setAttribute("conferenceList",
                conferenceService.findAllConference(((User) request.getSession().getAttribute("user")).getId()));
            return "/WEB-INF/admin/adminbasic.jsp";
    }
}
