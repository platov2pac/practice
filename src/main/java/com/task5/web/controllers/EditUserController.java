package com.task5.web.controllers;

import com.task5.dto.Role;
import com.task5.dto.User;
import com.task5.services.UserService;
import com.task5.services.validators.EditUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/edituser.jhtml")
@RestController
@RequestMapping("/edituser.jhtml")
@CrossOrigin(origins = "http://localhost:4200")
public class EditUserController {
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("editUserValidator")
    private EditUserValidator editUserValidator;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    protected @ResponseBody
    User doGet(@RequestParam(value = "loginUser", required = false) String loginUserToEdit) {
        User user = new User();
        try {
            if (loginUserToEdit != null) {
                user = userService.findByLoginWithoutPass(loginUserToEdit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @PostMapping
    protected void doPost(@RequestBody User user, @RequestParam(required = false) String loginUser, BindingResult bindingResult
    ) {
        editUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            // return "/editUser";
        }
        List<Role> userRoles = new ArrayList<>();
        user.getRoles().forEach(role -> {
            userRoles.add(new Role(role.getName()));
        });
        if (loginUser != null) {
            try {
                User userForUserId = userService.findByLogin(loginUser);
                userService.update(
                        userForUserId.getId(),
                        loginUser,
                        user.getLogin(),
                        passwordEncoder.encode(user.getPassword()),
                        user.getEmail(),
                        user.getDob(),
                        (ArrayList<Role>) userRoles);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            User newUser = new User(user.getLogin(), passwordEncoder.encode(user.getPassword()), userRoles, user.getEmail(), user.getDob());
            try {
                userService.create(newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @ModelAttribute("allRolesInApp")
    public List<Role> getAllRolesInApp() {
        List<Role> allRoles = new ArrayList<Role>();
        allRoles.add(new Role("ROLE_ADMIN"));
        allRoles.add(new Role("ROLE_USER"));
        return allRoles;
    }
}
