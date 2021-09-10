package com.task5.web.controllers;

import com.task5.dto.User;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/deleteUser.jhtml")
@CrossOrigin(origins = "http://localhost:4200")
public class DeleteController {

    @Autowired
    private UserService userService;

    @GetMapping
    protected void doGet(@RequestParam String deletableLogin) throws IOException {
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
    }
}
