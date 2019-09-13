package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;

public class ShowPresentationList implements Command {
    PresentationService presentationService ;

    public ShowPresentationList(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("presentationList", presentationService.findAllPresentation());
        return "/WEB-INF/user/presentationlist.jsp";
    }
}
