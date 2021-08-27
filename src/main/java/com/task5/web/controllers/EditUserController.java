package com.task5.web.controllers;

import com.task5.dto.Role;
import com.task5.dto.User;
import com.task5.services.UserService;
import com.task5.services.validators.EditUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/edituser.jhtml")
@Controller
@RequestMapping("edituser.jhtml")
public class EditUserController {
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("editUserValidator")
    private EditUserValidator editUserValidator;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    protected String doGet(Model model, @RequestParam(value = "loginUser", required = false) String loginUserToEdit) {
        User user = new User();
        try {
            if (loginUserToEdit != null) {
                user = userService.findByLoginWithoutPass(loginUserToEdit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("user", user);
        model.addAttribute("loginUser", loginUserToEdit);
        return "/editUser";
    }

    @PostMapping
    protected String doPost(@ModelAttribute User user, @RequestParam(required = false) String loginUser,
                            Model model, BindingResult bindingResult, HttpSession session) {
        // String sessionLogin = (String) session.getAttribute("sessionLogin");
//        try {
//            if (!user.getEmail().contains("@")
//                    || user.getLogin().equals("")
//                    || user.getDob().equals("")
//                    || (user.getPassword() != null && user.getPassword().equals(""))
//                    || userService.findByLogin(user.getLogin()) != null) {
//                String errors = "";
//                if (user.getLogin().equals("")) {
//                    errors += "login(empty) ";
//                }
//                if (userService.findByLogin(user.getLogin()) != null) {
//                    errors += "login(exist) ";
//                }
//                if (!user.getEmail().contains("@")) {
//                    errors += "email ";
//                }
//                if (user.getPassword() != null && user.getPassword().equals("")) {
//                    errors += "password ";
//                }
//                if (user.getDob().equals("")) {
//                    errors += "dob";
//                }
//                model.addAttribute("loginUser", loginUser);
//                model.addAttribute("user", user);
//                model.addAttribute("editFailed", true);
//                return "/editUser";
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        UserDetails principal = (UserDetails) authentication.getPrincipal();
//        String sessionLogin = principal.getUsername();

        editUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginUser", loginUser);
            model.addAttribute("user", user);
            model.addAttribute("editFailed", true);
            return "/editUser";
        }

        if (loginUser != null) {
//            if (loginUser.equals(sessionLogin)) {
//
//                session.setAttribute("login", user.getLogin());
//            }
            try {
                User userForUserId = userService.findByLogin(loginUser);
                userService.update(
                        userForUserId.getId(),
                        loginUser,
                        user.getLogin(),
                        passwordEncoder.encode(user.getPassword()),
                        user.getEmail(),
                        user.getDob(),
                        (ArrayList<Role>) user.getRoles());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "redirect:/listUsers.jhtml";
        } else {
            User newUser = new User(user.getLogin(), passwordEncoder.encode(user.getPassword()), user.getRoles(), user.getEmail(), user.getDob());
            try {
                userService.create(newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "redirect:/listUsers.jhtml";
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
