package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;

public class SaveEditPresentation implements Command {
    private PresentationService presentationService = new PresentationService();

    @Override
    public String execute(HttpServletRequest request) {
        String chooseConferenceID = request.getParameter("chooseConferenceID");
        String chooseSpeakerID = request.getParameter("chooseSpeakerID");
        String theme = request.getParameter("theme");
        String presentationEditId = request.getParameter("presentationEditId");
        System.out.println("chooseConferenceID: " + chooseConferenceID);
        System.out.println("chooseSpeakerID: " + chooseSpeakerID);
        System.out.println("theme: " + theme);
        System.out.println("presentationEditId: " + presentationEditId);
        presentationService.presentationEdit(presentationEditId, theme, chooseSpeakerID, chooseConferenceID);
        return "/conference/user/presentationlist";
    }
}
