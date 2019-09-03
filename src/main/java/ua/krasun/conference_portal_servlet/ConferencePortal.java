package ua.krasun.conference_portal_servlet;


import ua.krasun.conference_portal_servlet.command.Command;
import ua.krasun.conference_portal_servlet.command.Exception;
import ua.krasun.conference_portal_servlet.command.LogOut;
import ua.krasun.conference_portal_servlet.command.Login;
import ua.krasun.conference_portal_servlet.command.Registration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConferencePortal extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("logout", new LogOut());
        commands.put("exception", new Exception());
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
        if(page.contains("redirect")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }

//      response.sendRedirect("/welcome.jsp");
//      request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}


