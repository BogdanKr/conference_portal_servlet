package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class RolePath implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        request.getSession().setAttribute("conferenceList", conferenceService.findAllConference());
        if (request.getSession().getAttribute("role")==null)
            return "redirect:/index.jsp";
        else if (request.getSession().getAttribute("role").equals(Role.ADMIN))
            return "/WEB-INF/admin/adminbasic.jsp";
        else if (request.getSession().getAttribute("role").equals(Role.SPEAKER))
            return "/WEB-INF/speaker/speakerbasic.jsp";
        else
            return "/WEB-INF/user/userbasic.jsp";
    }
}
