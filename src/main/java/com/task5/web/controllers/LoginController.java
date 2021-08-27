//package com.task5.web.controllers;
//
//import com.task5.dto.User;
//import com.task5.services.UserService;
//import com.task5.services.validators.LoginValidator;
//import com.task5.web.forms.LoginForm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/auth.jhtml")
//public class LoginController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    @Qualifier("loginValidator")
//    private LoginValidator loginValidator;
//
//    @GetMapping
//    public String doGet(HttpSession session, @RequestParam(value = "lang", required = false) String lang) {
//        session.setAttribute("lang", lang);
//        boolean userIsLogged = session != null && session.getAttribute("sessionLogin") != null;
//        if (!userIsLogged) {
//            return "/login";
//
//        } else {
//            return "redirect:/welcome";
//        }
//    }
//
//    @PostMapping
//    public String doPost(@ModelAttribute("loginForm") LoginForm loginForm,
//                         HttpSession session, Model model, BindingResult bindingResult) {
//        loginValidator.validate(loginForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("login", loginForm.getLogin());
//            model.addAttribute("password", loginForm.getPassword());
//            return "login";
//        }
//        User user = null;
//        try {
//            user = userService.findByLoginAndPassword(loginForm.getLogin(), loginForm.getPassword());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (user != null) {
//            session.setAttribute("sessionLogin", user.getLogin());
//            session.setAttribute("roles", user.getRoles());
//            // session.setAttribute("lang", loginForm.getLanguage());
//            return "redirect:/welcome.jhtml";
//        } else {
//            bindingResult.rejectValue("login", "login.password.wrong");
//            bindingResult.rejectValue("password", "password.wrong");
//            model.addAttribute("login", loginForm.getLogin());
//            model.addAttribute("password", loginForm.getPassword());
//            return "login";
//        }
//
//    }
//
//    @ModelAttribute("loginForm")
//    public LoginForm getLoginForm() {
//        return new LoginForm();
//
//    }
////
////    @ModelAttribute("allLang")
////    public List<String> getAllLang() {
////        List<String> allLang = new ArrayList<>();
////        allLang.add("ru");
////        allLang.add("en");
////        return allLang;
////    }
//}
