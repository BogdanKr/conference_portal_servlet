package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class MyConfRegList implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        User currentUser = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("conferenceList",
                conferenceService.findAllUserRegistrations(currentUser));
        request.setAttribute("edit", true);
        if (currentUser.getRole().equals(Role.ADMIN))
            return "/WEB-INF/admin/conferencelistadmin.jsp";
        else if (currentUser.getRole().equals(Role.SPEAKER))
            return "/WEB-INF/speaker/conferencelistspeaker.jsp";
        else
            return "/WEB-INF/user/conferencelist.jsp";
    }
}
