package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditPresentation implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        PresentationService presentationService = new PresentationService();
        ConferenceService conferenceService = new ConferenceService();
        Optional<String> locale = Optional.ofNullable((String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        String presentationEditId = request.getParameter("presentationEditId");

        request.setAttribute("conferenceList",
                conferenceService.findAllConference(((User) request.getSession().getAttribute("user")).getId()));
        request.setAttribute("speakerList", userService.findAllSpeaker());
        Presentation presentation;
        try {
            presentation = presentationService.findById(Integer.parseInt(presentationEditId)).orElse(new Presentation());
            request.setAttribute("presentation", presentation);
        } catch (NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.invalid.input"));
        }
        if (request.getSession().getAttribute("role").equals(Role.ADMIN))
            return "/WEB-INF/admin/editpresentationadmin.jsp";
        else return "/WEB-INF/speaker/editpresentationspeaker.jsp";
    }
}
