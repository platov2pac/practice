package com.task5.web.filter;

import com.task5.dto.Role;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Order(1)
@WebFilter(filterName = "Access",urlPatterns = "*.jhtml")

public class Access implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String loginURL = req.getContextPath() + "/auth.jhtml";
        String editUserPasswordURL = req.getContextPath() + "/editpassword.jhtml";
        String logoutURL = req.getContextPath() + "/logout.jhtml";
        String welcomeURL = req.getContextPath() + "/welcome.jhtml";
        boolean isLoginURL = req.getRequestURI().equals(loginURL);
        boolean isEditUserPasswordURL = req.getRequestURI().equals(editUserPasswordURL);
        boolean isLogoutURL = req.getRequestURI().equals(logoutURL);
        boolean isWelcomeURL = req.getRequestURI().equals(welcomeURL);
        HttpSession session = req.getSession(false);
        System.out.println(req.getRequestURI());
        System.out.println(req.getContextPath() + "/logout.jhtml");
        if (isLoginURL || isEditUserPasswordURL || isLogoutURL || isWelcomeURL) {
            filterChain.doFilter(req, resp);
        } else {
            ArrayList<Role> roles = (ArrayList<Role>) session.getAttribute("roles");
            roles.forEach(role -> {
                if (!role.getName().equals("admin")) {
                    try {
                        resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        filterChain.doFilter(req, resp);
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                }

            });

        }
    }

    @Override
    public void destroy() {

    }
}