package ua.krasun.conference_portal_servlet.controller.command;

import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.nonNull;

public class Login implements Command {
    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " " + password);

        System.out.println("entering DB : ");
        userService.findAllUsers().forEach(System.out::println);

        if (nonNull(request.getSession().getAttribute("userEmail"))) return "/welcome.jsp";
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            if (email != null) request.setAttribute("error", true);
            return "/login.jsp";
        } else {
            //todo: check login with DB

//            Optional<Teacher> teacher = teacherService.login(name);
//            if( teacher.isPresent() && teacher.get().getPassHash()
//                    == pass.hashCode()){
//                request.getSession().setAttribute("teacher" , teacher.get());
//                logger.info("Teacher "+ name+" logged successfully.");
//                return "/WEB-INF/studentlist.jsp";
//
//            }
//            logger.info("Invalid attempt of login user:'"+ name+"'");
//            return "/login.jsp";

            if (CommandUtility.checkUserIsLogged(request, email)) {
                throw new RuntimeException("You already logged");
            }
            if (email.equals("a@a")) {
                CommandUtility.setUserRole(request, Role.ADMIN, email);
                return "redirect:/conference/admin";
            } else if (email.equals("u@u")) {
                CommandUtility.setUserRole(request, Role.USER, email);
                return "/WEB-INF/user/userbasic.jsp";
            } else {
                CommandUtility.setUserRole(request, Role.GUEST, email);
                return "redirect:/welcome.jsp";
            }
        }
    }
}
//  redirect:
