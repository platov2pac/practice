package com.task5.web.controllers;

import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/editpassword.jhtml")
@Controller
@RequestMapping("/editpassword.jhtml")
public class EditPasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

   @PostMapping
    protected String doPost(@RequestParam String newPassword, @RequestParam(name = "sessionLogin") String login) throws ServletException, IOException {
        try {
            userService.updatePassword(login,passwordEncoder.encode(newPassword));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/welcome.jhtml";
    }
}
