package ua.krasun.conference_portal_servlet.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DeletePresentation implements Command {
    private static final Logger logger = LogManager.getLogger(DeletePresentation.class);

    @Override
    public String execute(HttpServletRequest request) {
        PresentationService presentationService = new PresentationService();
        String presentationId = request.getParameter("presentationId");
        if (Optional.ofNullable(presentationId).isEmpty()) {
            return "redirect:" + request.getHeader("referer");
        }

        try {
            presentationService.deletePresentation(Long.parseLong(presentationId));
            logger.info("Presentation was deleted, ID: " + presentationId);
        } catch (NumberFormatException e) {
            logger.info("Can't delete presentation, ID: " + presentationId);
        }
        return "redirect:" + request.getHeader("referer");
    }
}
