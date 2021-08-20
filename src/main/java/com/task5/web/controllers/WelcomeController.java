package com.task5.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/welcome.jhtml")
public class WelcomeController {
    @GetMapping
    public String doGet() {
        return "/welcome";

    }
}
