package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserContoller {
    final UserService userService;

    @GetMapping("/user/all")
    public String getUsers(Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        return "/user/users";

    }

    @GetMapping("/user/id/{id}")
    public String showUserDetail(@PathVariable Integer id, Model model) {
        var user = userService.getUser(id);
        model.addAttribute("user", user);
        user.ifPresent(User -> model.addAttribute("users", user.get()));
        return "/user/user-details";

    }

}
