package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;

public class AddPresentation implements Command {
    PresentationService presentationService;

    public AddPresentation(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String theme = request.getParameter("theme");
        if (theme == null) return "/";
        String confId = request.getParameter("conferenceId");
        User currentUser = (User) request.getSession().getAttribute("user");

        return null;
    }
}
