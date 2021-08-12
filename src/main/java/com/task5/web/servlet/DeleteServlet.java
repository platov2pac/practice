package com.task5.web.servlet;

import com.task5.dto.User;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser.jhtml")
public class DeleteServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deletableLogin = req.getParameter("deletableLogin");
        User deletableUser = null;
        try {
            deletableUser = userService.findByLogin(deletableLogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userService.delete(deletableUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/listUsers.jhtml");
    }
}
