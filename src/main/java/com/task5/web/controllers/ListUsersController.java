package com.task5.web.controllers;

import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/listUsers.jhtml")
//@WebServlet("/listUsers.jhtml")
public class ListUsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String doGet(Model model) {
        try {
            model.addAttribute("users", userService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/userList";
    }
}

