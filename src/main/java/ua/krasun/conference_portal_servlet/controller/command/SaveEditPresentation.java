package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class SaveEditPresentation implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        PresentationService presentationService = new PresentationService();
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        String chooseConferenceID = request.getParameter("chooseConferenceID");
        String chooseSpeakerID = request.getParameter("chooseSpeakerID");
        String theme = request.getParameter("theme");
        String presentationEditId = request.getParameter("presentationEditId");
        try {
            presentationService.presentationEdit(presentationEditId, theme, chooseSpeakerID, chooseConferenceID);
            request.setAttribute("success", true);
            request.setAttribute("message", bundle.getString("info.success.save"));
        } catch (java.lang.Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.cant.save"));
        }
        return "/conference/user/presentationlist";
    }
}
