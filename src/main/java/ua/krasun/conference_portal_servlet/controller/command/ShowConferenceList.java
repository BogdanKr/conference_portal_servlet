package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class ShowConferenceList implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        request.getSession().setAttribute("conferenceList",
                conferenceService.findAllConference(((User) request.getSession().getAttribute("user")).getId()));
        request.setAttribute("edit", true);
        if (request.getSession().getAttribute("role").equals(Role.ADMIN))
            return "/WEB-INF/admin/conferencelistadmin.jsp";
        else if (request.getSession().getAttribute("role").equals(Role.SPEAKER))
            return "/WEB-INF/speaker/conferencelistspeaker.jsp";
        else
            return "/WEB-INF/user/conferencelist.jsp";
    }
}
