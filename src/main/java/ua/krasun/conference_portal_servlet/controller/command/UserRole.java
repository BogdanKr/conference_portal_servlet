package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class UserRole implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.showPaginationConfList(request);
        return "/WEB-INF/user/userbasic.jsp";
    }
}
