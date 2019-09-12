package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class ShowConferenceList implements Command {
    ConferenceService conferenceService = new ConferenceService();
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("conferenceList", conferenceService.findAllConference());
        request.setAttribute("edit", true);
        return "/WEB-INF/conferencelist.jsp";
    }
}
