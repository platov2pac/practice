package com.task5.web.controllers;

import com.task5.dto.User;
import com.task5.services.UserService;
import com.task5.services.errorsProcessing.NotFoundException;
import com.task5.services.validators.LoginValidator;
import com.task5.web.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/auth.jhtml")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("loginValidator")
    private LoginValidator loginValidator;

    @PostMapping
    public User doPost(@RequestBody LoginForm loginForm,
                       BindingResult bindingResult) {
        loginValidator.validate(loginForm, bindingResult);
        if (bindingResult.hasErrors()) {

        }
        User user;
        try {
            user = userService.findByLogin(loginForm.getLogin());
            if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
                return user;
            }
            else {
                throw new NotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
