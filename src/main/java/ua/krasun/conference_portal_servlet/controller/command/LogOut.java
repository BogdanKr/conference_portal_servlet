package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
//        CommandUtility.deleteUserFromContextAndSession(request);
        request.getSession().invalidate();
        return "redirect:/index.jsp";
    }
}
