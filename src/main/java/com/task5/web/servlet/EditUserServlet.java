package com.task5.web.servlet;

import com.task5.dto.Role;
import com.task5.dto.User;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@WebServlet("/edituser.jhtml")
@Controller
@RequestMapping("edituser.jhtml")
public class EditUserServlet {
    @Autowired
    private UserService userService;

    @GetMapping
    protected String doGet(Model model, @RequestParam(value = "loginUser", required = false) String loginUserToEdit) {
        User user = new User();
        try {
            if (loginUserToEdit != null) {
                user = userService.findByLogin(loginUserToEdit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("user", user);
        model.addAttribute("loginUser", loginUserToEdit);
        return "/editUser";
    }

    @PostMapping
    protected String doPost(@ModelAttribute("editableUser") User user, @RequestParam(required = false) String loginUser,
                            HttpSession session, Model model) {
        // System.out.println();
        String sessionLogin = (String) session.getAttribute("sessionLogin");
        // return "/editUser";
//        HttpSession session = req.getSession();
//        String loginUser = req.getParameter("loginUser");
//        String newLogin = req.getParameter("newLogin");
//        String email = req.getParameter("email");
//        String dob = req.getParameter("dob");
//        String[] roles = req.getParameterValues("role");
//        ArrayList<Role> userRoles = new ArrayList<>();
//        if (roles != null) {
//            Arrays.stream(roles).forEach(role -> userRoles.add(new Role(role)));
//        }
//        String password = req.getParameter("password");
//        String loginFromSession = (String) session.getAttribute("sessionLogin");
        try {
            if (!user.getEmail().contains("@")
                    || user.getLogin().equals("")
                    || user.getDob().equals("")
                    || (user.getPassword() != null && user.getPassword().equals(""))
                    || userService.findByLogin(user.getLogin()) != null) {
                String errors = "";
                if (user.getLogin().equals("")) {
                    errors += "login(empty) ";
                }
                if (userService.findByLogin(user.getLogin()) != null) {
                    errors += "login(exist) ";
                }
                if (!user.getEmail().contains("@")) {
                    errors += "email ";
                }
                if (user.getPassword() != null && user.getPassword().equals("")) {
                    errors += "password ";
                }
                if (user.getDob().equals("")) {
                    errors += "dob";
                }
//                req.setAttribute("loginUser", loginUser);
//                req.setAttribute("errors", errors);
//                User user = new User(newLogin, userRoles, email, dob);
                model.addAttribute("loginUser", loginUser);
                model.addAttribute("user", user);
                model.addAttribute("editFailed", true);
                // req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp?editFailed=true").forward(req, resp);
                return "/editUser";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (loginUser != null) {
            if (loginUser.equals(sessionLogin)) {

                session.setAttribute("login", user.getLogin());
            }
            try {
                User userForUserId = userService.findByLogin(loginUser);
                userService.update(
                        userForUserId.getId(),
                        loginUser,
                        user.getLogin(),
                        user.getEmail(),
                        user.getDob(),
                        (ArrayList<Role>) user.getRoles());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "redirect:/listUsers.jhtml";
//            resp.sendRedirect(req.getContextPath() + "/listUsers.jhtml");
        } else {
            User newUser = new User(user.getLogin(), user.getPassword(), user.getRoles(), user.getEmail(), user.getDob());
            try {
                userService.create(newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "redirect:/listUsers.jhtml";
            // resp.sendRedirect(req.getContextPath() + "/listUsers.jhtml");
        }
    }

    @ModelAttribute("allRolesInApp")
    public List<Role> getAllRolesInApp() {
        List<Role> allRoles = new ArrayList<Role>();
        allRoles.add(new Role("admin"));
        allRoles.add(new Role("user"));
        return allRoles;
    }
}
