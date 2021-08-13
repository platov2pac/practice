package com.task5.web.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/logout.jhtml")
public class LogoutServlet extends HttpServlet {

    @GetMapping
    protected String doGet(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/auth.jhtml";
    }
}
