package ua.krasun.conference_portal_servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConferencePortal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        processRequest(req, resp);
//
//    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        response.getWriter().print("Hello! from servlet : " + path + " ");
        path = path.replaceAll(".*/app", "");
        response.getWriter().print("<br>");
//        response.getWriter().print("replaceAll(\".*/app\", \"\") path: " + path);
//        response.sendRedirect("/welcome.jsp");

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}


