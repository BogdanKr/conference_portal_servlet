package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class ShowConferenceList implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        request.getSession().setAttribute("conferenceList", conferenceService.findAllConference());
        request.setAttribute("edit", true);
        return "/WEB-INF/user/conferencelist.jsp";
    }
}
