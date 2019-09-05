package ua.krasun.conference_portal_servlet.controller.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String email = (String) httpSessionEvent.getSession()
                .getAttribute("userEmail");
        System.out.println(email);
        System.out.println(loggedUsers);
        loggedUsers.remove(email);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
        System.out.println("listener works");
        System.out.println(loggedUsers);

    }
}
