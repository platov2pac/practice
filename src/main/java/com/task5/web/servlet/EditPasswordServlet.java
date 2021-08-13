package com.task5.web.servlet;

import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/editpassword.jhtml")
@Controller
@RequestMapping("/editpassword.jhtml")
public class EditPasswordServlet {
    @Autowired
    private UserService userService;

   @PostMapping
    protected String doPost(@RequestParam String newPassword, @SessionAttribute String login) throws ServletException, IOException {
        try {
            userService.updatePassword(login, newPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/welcome.jhtml";
    }
}
