package ua.krasun.conference_portal_servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ConfRegistration implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("делаем редирект на ту же страничку");
        return null;
    }
}
