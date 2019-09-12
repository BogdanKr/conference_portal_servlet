package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class SpeakerRole implements Command {
    @Override
    public String execute(HttpServletRequest request) {

            return "/WEB-INF/speaker/speakerbasic.jsp";
    }
}
