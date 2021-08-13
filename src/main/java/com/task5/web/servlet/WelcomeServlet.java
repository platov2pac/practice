package com.task5.web.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/welcome.jhtml")
public class WelcomeServlet {
    @GetMapping
    public String doGet() {
        return "/welcome";

    }
}
