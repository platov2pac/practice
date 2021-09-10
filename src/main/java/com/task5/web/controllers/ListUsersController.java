package com.task5.web.controllers;

import com.task5.dto.User;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/listUsers.jhtml")
@CrossOrigin(origins = "http://localhost:4200")
//@WebServlet("/listUsers.jhtml")
public class ListUsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody
    List<User> doGet() {
        List<User> users = new ArrayList<>();
        try {
            users = userService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}

