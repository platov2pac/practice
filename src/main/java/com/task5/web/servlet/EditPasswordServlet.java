package com.task5.web.servlet;

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

@WebServlet("/editpassword.jhtml")
public class EditPasswordServlet extends HttpServlet {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        String password = req.getParameter("newPassword");
        try {
            userService.updatePassword(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
    }
}
