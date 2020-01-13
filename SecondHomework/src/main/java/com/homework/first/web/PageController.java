package com.homework.first.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@Slf4j
public class PageController {

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("path", "home");
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("path", "login");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("path", "login");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("path","registration");
        return "registration";
    }

    @GetMapping("/start")
    public String start(Model model){
        model.addAttribute("path", "start");
        return "start";
    }
}
