package com.task5.web.servlet;

import com.task5.dto.User;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@Controller
@RequestMapping("/auth.jhtml")
public class LoginServlet {
    @Autowired
    private UserService userService;

    @GetMapping
    public String doGet(HttpSession session) {

        boolean userIsLogged = session != null && session.getAttribute("sessionLogin") != null;
        if (!userIsLogged) {
            return "/login";

        } else {
            return "redirect:/welcome";
        }
    }

    @PostMapping
    public String doPost(@RequestParam String login, @RequestParam String password, HttpSession session, Model model) {
        User user = null;
        try {
            user = userService.findByLoginAndPassword(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            session.setAttribute("sessionLogin", user.getLogin());
            session.setAttribute("roles", user.getRoles());
            return "redirect:/welcome.jhtml";
        } else {
            model.addAttribute("login", login);
            model.addAttribute("password", password);
            model.addAttribute("authFailed", true);
            return "login";
        }
    }
}
