package com.example.demo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.mappers.UserSaveDtoUserMapper;
import com.example.demo.models.Address;
import com.example.demo.models.dto.UserSaveDto;
import com.example.demo.services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping("/user/all")
    public String getUsers(Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/user/id/{id}")
    public String getUsers(@PathVariable Integer id, Model model) {
        var userOptional = userService.getUser(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            // todo: 404
        }
        return "user/user";
    }

    @GetMapping("/user/add")
    public String addUserForm(Model model) {
        model.addAttribute("addresses", userService.getAddresses());
        return "user/save-user";
    }

    @GetMapping("/user/edit/{userId}")
    public String editUserForm(@PathVariable Integer userId, Model model) {
        var optional = userService.getUser(userId);
        if (optional.isPresent()) {
            model.addAttribute("user", optional.get());
        } else {
            // todo: 404
        }
        model.addAttribute("addresses", userService.getAddresses());
        return "user/save-user";
    }

    @PostMapping("/user/address/add")
    public String saveUserAddress(@Valid Address address) {
        userService.saveAddress(address);
        return "redirect:/user/add";
    }

    @PostMapping("/user/save")
    public String saveUser(UserSaveDto userSaveDto, RedirectAttributes redirectAttributes) {
        var user = UserSaveDtoUserMapper.fromDtoToEntity(userSaveDto);
        if (userService.existsByEmail(userSaveDto.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email jest zajęty");
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/user/add";
        } else {
            userService.saveUser(user);
            return "redirect:/user/all";
        }
    }

    @PostMapping("/user/update/{userId}")
    public String userUpdate(@PathVariable Integer userId, UserSaveDto userSaveDto) {
        var user = UserSaveDtoUserMapper.fromDtoToEntity(userSaveDto);
        userService.updateUser(userId, user);
        return "redirect:/user/all";
    }

    @GetMapping("/user/remove/{userId}")
    public String removeUserById(@PathVariable Integer userId) {
        userService.removeUserById(userId);
        return "redirect:/user/all";
    }
}
