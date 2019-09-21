package ua.krasun.conference_portal_servlet;

import ua.krasun.conference_portal_servlet.controller.command.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConferencePortal extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {

        commands.put("", new RolePath());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("logout", new LogOut());
        commands.put("admin", new AdminRole());
        commands.put("user", new UserRole());
        commands.put("speaker", new SpeakerRole());
        commands.put("admin/userlist", new UserList());
        commands.put("admin/edit", new AdminEdit());
        commands.put("admin/addconference", new AddConference());
        commands.put("admin/adduser", new AdminAddUser());
        commands.put("speaker/addpresentation", new AddPresentation());
        commands.put("speaker/editpresentation", new EditPresentation());
        commands.put("admin/editpresentation", new EditPresentation());
        commands.put("speaker/saveeditpresentation", new SaveEditPresentation());
        commands.put("user/edit", new UserEdit());
        commands.put("speaker/edit", new UserEdit());
        commands.put("user/delete_profile", new DeleteProfile());
        commands.put("speaker/delete_profile", new DeleteProfile());
        commands.put("admin/delete_profile", new DeleteProfile());
        commands.put("admin/delete_conference", new DeleteConference());
        commands.put("admin/delete_presentation", new DeletePresentation());
        commands.put("speaker/delete_presentation", new DeletePresentation());
        commands.put("user/conferencelist", new ShowConferenceList());
        commands.put("speaker/conferencelist", new ShowConferenceList());
        commands.put("admin/conferencelist", new ShowConferenceList());
        commands.put("user/myconferenceregistration", new MyConfRegList());
        commands.put("speaker/myconferenceregistration", new MyConfRegList());
        commands.put("admin/myconferenceregistration", new MyConfRegList());
        commands.put("user/presentationlist", new ShowPresentationList());
        commands.put("speaker/presentationlist", new ShowPresentationList());
        commands.put("admin/presentationlist", new ShowPresentationList());
        commands.put("user/conference_registration", new ConfRegistration());
        commands.put("speaker/conference_registration", new ConfRegistration());
        commands.put("admin/conference_registration", new ConfRegistration());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/conference/", "");

        Command command = commands.getOrDefault(path, (r) -> "/conference/");
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}


