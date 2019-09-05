package ua.krasun.conference_portal_servlet;

import ua.krasun.conference_portal_servlet.controller.command.*;
import ua.krasun.conference_portal_servlet.controller.command.Exception;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ConferencePortal extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        UserService userService = new UserService();
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("login", new Login(userService));
        commands.put("registration", new Registration(userService));
        commands.put("logout", new LogOut());
        commands.put("exception", new Exception());
        commands.put("admin", new AdminRole());

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
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURI());
        String path = request.getRequestURI();
        path = path.replaceAll(".*/conference/", "");

        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
//      response.sendRedirect("/welcome.jsp");
//      request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }


}


