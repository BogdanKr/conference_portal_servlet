package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("userEmail") == null) return "redirect:/";

        request.getSession().invalidate();
        request.setAttribute("success", true);
        request.setAttribute("message", "Success LogOut");
        return "/index.jsp";
    }
}
