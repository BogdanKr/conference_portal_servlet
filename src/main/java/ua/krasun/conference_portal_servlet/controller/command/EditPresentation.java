package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditPresentation implements Command {
    private PresentationService presentationService = new PresentationService();
    private ConferenceService conferenceService = new ConferenceService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String presentationEditId = request.getParameter("presentationEditId");
//        String theme = request.getParameter("theme");

        request.setAttribute("conferenceList", conferenceService.findAllConference());
        request.setAttribute("speakerList", userService.findAllSpeaker());
        Presentation presentation = presentationService.findById(Integer.parseInt(presentationEditId)).orElse(new Presentation());
        request.setAttribute("presentation", presentation);
        if (request.getSession().getAttribute("role").equals(Role.ADMIN))
        return "/WEB-INF/admin/editpresentationadmin.jsp";
        else return "/WEB-INF/speaker/editpresentationspeaker.jsp";
    }
}
