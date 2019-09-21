package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class SaveEditPresentation implements Command {
    private static final Logger logger = LogManager.getLogger(SaveEditPresentation.class);

    @Override
    public String execute(HttpServletRequest request) {
        PresentationService presentationService = new PresentationService();
        Optional<String> locale = Optional.ofNullable((String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        try {
            int chooseConferenceID = Integer.parseInt(request.getParameter("chooseConferenceID"));
            int chooseSpeakerID = Integer.parseInt(request.getParameter("chooseSpeakerID"));
            String theme = request.getParameter("theme");
            int presentationEditId = Integer.parseInt(request.getParameter("presentationEditId"));
            presentationService.presentationEdit(presentationEditId, theme, chooseSpeakerID, chooseConferenceID);
            request.setAttribute("success", true);
            request.setAttribute("message", bundle.getString("info.success.save"));
        } catch (NumberFormatException | WrongInputException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.cant.save"));
            logger.warn("Exception: " + e.getMessage());
        }
        return "/conference/user/presentationlist";
    }
}
