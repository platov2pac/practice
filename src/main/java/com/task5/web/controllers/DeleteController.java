package com.task5.web.controllers;

import com.task5.dto.User;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/deleteUser.jhtml")
public class DeleteController {

    @Autowired
    private UserService userService;

    @GetMapping
    protected String doGet(@RequestParam String deletableLogin) throws IOException {
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
        return "redirect:/listUsers.jhtml";
    }
}
