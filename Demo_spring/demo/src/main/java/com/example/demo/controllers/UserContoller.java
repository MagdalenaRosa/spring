package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserContoller {
    final UserService userService;

    @GetMapping("/users/all")
    public String getUsers(Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("db", userService.getUsers());
        return "/user/users";

    }

}
