package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminRole implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.showPaginationConfList(request);

        return "/WEB-INF/admin/adminbasic.jsp";
    }
}
