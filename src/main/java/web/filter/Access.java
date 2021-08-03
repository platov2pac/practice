package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Access")
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

        if (isLoginURL || isEditUserPasswordURL || isLogoutURL || isWelcomeURL) {
            filterChain.doFilter(req, resp);
        } else {
            String role = (String) session.getAttribute("role");
            if (!role.equals("admin")) {
                resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
            } else {
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
