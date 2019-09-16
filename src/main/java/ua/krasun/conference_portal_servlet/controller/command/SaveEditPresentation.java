package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;

public class SaveEditPresentation implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        PresentationService presentationService = new PresentationService();
        String chooseConferenceID = request.getParameter("chooseConferenceID");
        String chooseSpeakerID = request.getParameter("chooseSpeakerID");
        String theme = request.getParameter("theme");
        String presentationEditId = request.getParameter("presentationEditId");
        System.out.println("chooseConferenceID: " + chooseConferenceID);
        System.out.println("chooseSpeakerID: " + chooseSpeakerID);
        System.out.println("theme: " + theme);
        System.out.println("presentationEditId: " + presentationEditId);
        try {
            presentationService.presentationEdit(presentationEditId, theme, chooseSpeakerID, chooseConferenceID);
            request.setAttribute("success", true);
            request.setAttribute("message", "Success Save");
        } catch (java.lang.Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", "Can't save");
        }
        return "/conference/user/presentationlist";
    }
}
