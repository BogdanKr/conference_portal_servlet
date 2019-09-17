package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditPresentation implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        PresentationService presentationService = new PresentationService();
        ConferenceService conferenceService = new ConferenceService();
        String presentationEditId = request.getParameter("presentationEditId");

        request.setAttribute("conferenceList",
                conferenceService.findAllConference(((User) request.getSession().getAttribute("user")).getId()));
        request.setAttribute("speakerList", userService.findAllSpeaker());
        Presentation presentation = null;
        try {
            presentation = presentationService.findById(Integer.parseInt(presentationEditId)).orElse(new Presentation());
        } catch (NumberFormatException | SQLException e){
            request.setAttribute("error", true);
            request.setAttribute("message", "Invalid number");
        }
        request.setAttribute("presentation", presentation);
        if (request.getSession().getAttribute("role").equals(Role.ADMIN))
            return "/WEB-INF/admin/editpresentationadmin.jsp";
        else return "/WEB-INF/speaker/editpresentationspeaker.jsp";
    }
}
