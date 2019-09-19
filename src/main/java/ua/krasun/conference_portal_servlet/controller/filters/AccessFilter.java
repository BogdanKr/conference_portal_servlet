package ua.krasun.conference_portal_servlet.controller.filters;

import ua.krasun.conference_portal_servlet.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<String> locale = Optional.ofNullable( (String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        String path = request.getRequestURI();
        Role role = (Role) request.getSession().getAttribute("role");

        if ((path.contains("speaker") && (role == Role.USER || role == null))
                || (path.contains("user") && role == null)
                || (path.contains("admin") && role != Role.ADMIN)) {
            request.setAttribute("error", true);
            request.setAttribute("message", bundle.getString("info.access.denied"));
            request.getRequestDispatcher("/conference/").forward(request, response);
            return;
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

