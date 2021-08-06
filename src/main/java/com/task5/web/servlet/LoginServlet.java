package com.task5.web.servlet;

import com.task5.dto.User;
import com.task5.services.ServiceFactory;
import com.task5.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth.jhtml")
public class LoginServlet extends HttpServlet {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean userIsLogged = session != null && session.getAttribute("login") != null;
        if (!userIsLogged) {
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);

        } else {
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        User user = null;
        try {
            user = userService.findByLoginAndPassword(login, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", user.getLogin());
            session.setAttribute("roles", user.getRoles());
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        } else {
            req.setAttribute("login", login);
            req.setAttribute("pass", pass);
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp?authFailed=true").forward(req, resp);
        }

    }
}
