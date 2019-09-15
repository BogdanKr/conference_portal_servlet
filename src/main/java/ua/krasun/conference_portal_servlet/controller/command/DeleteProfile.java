package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class DeleteProfile implements Command {
    UserService userService;

    public DeleteProfile(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String deleteId = (String) request.getParameter("deleteId");
        System.out.println("delete id:" + deleteId);
        if (Optional.ofNullable(deleteId).isEmpty()) {
            return "redirect:/index.jsp";
        }
        User currentUser = (User) request.getSession().getAttribute("user");
        System.out.println("currentUser Id = " + currentUser.getId());
        if (currentUser.getId() == Long.parseLong(deleteId) || currentUser.getRole().equals(Role.ADMIN)) {
            try {
                userService.deleteUser(Long.parseLong(deleteId));
                request.setAttribute("error", true);
                request.setAttribute("message", "Profile deleted");
            } catch (SQLException e) {
                request.setAttribute("error", true);
                request.setAttribute("message", "Sorry, can't delete");
            }
        }
        if (request.getSession().getAttribute("role").equals(Role.ADMIN)) {
            return "/conference/admin/userlist";
        }
        request.getSession().invalidate();
        return "/index.jsp";
    }
}
