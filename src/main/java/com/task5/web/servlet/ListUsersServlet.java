package com.task5.web.servlet;

import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/listUsers.jhtml")
public class ListUsersServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            req.setAttribute("users", userService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(req, resp);
    }
}
