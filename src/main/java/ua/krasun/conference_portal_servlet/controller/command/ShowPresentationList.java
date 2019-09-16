package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowPresentationList implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        PresentationService presentationService = new PresentationService();
        try {
            Optional<String> speakerID = Optional.ofNullable(request.getParameter("speakerID"));
            if (speakerID.isEmpty())
                request.setAttribute("presentationList", presentationService.findAllPresentation());
            else
                request.setAttribute("presentationList", presentationService.findAllByAuthorId(Long.parseLong(speakerID.get())));
        } catch (NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", "Invalid number");
        }
        if (request.getSession().getAttribute("role").equals(Role.ADMIN))
            return "/WEB-INF/admin/presentationlistadmin.jsp";
        else if (request.getSession().getAttribute("role").equals(Role.SPEAKER))
            return "/WEB-INF/speaker/presentationlistspeaker.jsp";
        else
            return "/WEB-INF/user/presentationlist.jsp";

    }
}
