package com.sparta.project1.controller;

import com.sparta.project1.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        } else {
            model.addAttribute("username","");
        }
        return "index";
    }

    @GetMapping("/api/articles/detail")
    public String detail(@RequestParam("id") Long id) {
        return "/detail.html";
    }

//    @GetMapping("/visitor")
//    public String visitorPass(Model model) {
//        model.addAttribute("username", "visitor");
//        return "index";
//    }

}

