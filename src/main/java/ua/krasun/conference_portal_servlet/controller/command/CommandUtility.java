package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

class CommandUtility {
    static void setUserRole(HttpServletRequest request, Role role, String email) {
        HttpSession session = request.getSession();
        session.setAttribute("userEmail", email);
        session.setAttribute("role", role);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String email) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null) return false;
        if (loggedUsers.stream().anyMatch(email::equalsIgnoreCase)) return true;
        loggedUsers.add(email);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }


    static void setUserInSession(User user, HttpServletRequest request) {
        request.getSession().setAttribute("user", user);
    }

    static void showPaginationConfList(HttpServletRequest request) {
        ConferenceService conferenceService = new ConferenceService();
        List<Conference> list = conferenceService.findAllConference(((User) request.getSession().getAttribute("user")).getId());
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        int page = 1;
        int recordsPerPage = 6;
        int noOfRecords = list.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        try {
            if (request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.invalid.input"));
        }
        if (page > noOfPages) page = noOfPages;
        List<Conference> listPage = list.stream().skip((page - 1) * recordsPerPage)
                .limit(recordsPerPage).collect(Collectors.toList());

        request.getSession().setAttribute("conferenceList", listPage);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
    }
}
