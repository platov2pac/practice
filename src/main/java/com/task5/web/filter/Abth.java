package com.task5.web.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Abth", urlPatterns =
        "*.jhtml")
public class Abth implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("авторизация");
        if (servletRequest.getCharacterEncoding() == null) {
            servletRequest.setCharacterEncoding("UTF-8");
            servletResponse.setCharacterEncoding("UTF-8");
        }

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        if (req.getRequestURI().matches(".*[css|jpg|png|gif|js].*")) {
//            filterChain.doFilter(req, resp);
//            return;
//        }

        HttpSession session = req.getSession(false);
        String loginURL = req.getContextPath() + "/auth.jhtml";
        boolean isLoginURL = req.getRequestURI().equals(loginURL);
        boolean isUserLogged = session != null && session.getAttribute("login") != null;
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isLoginURL || isUserLogged) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginURL);
        }
    }

    @Override
    public void destroy() {

    }
}
