package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Address;
import com.example.demo.models.PhoneNumber;
import com.example.demo.models.User;
import com.example.demo.models.UserDetails;
import com.example.demo.models.dto.UserSaveDto;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;
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
        user.ifPresent(User -> model.addAttribute("user", user.get()));
        return "/user/user-details";

    }

    @GetMapping("/user/remove/id/{id}")
    public String removeUser(@PathVariable Integer id) {
        userService.removeUserById(id);
        return "redirect:/user/all";

    }

    @GetMapping("/user/add")
    public String addUserForm(Model model) {
        model.addAttribute("action", "/user/address/add");
        model.addAttribute("addresses", userService.getAddresses());
        return "user/save-user";
    }

    @PostMapping("/user/address/add")
    public String saveUserAdress(@Valid Address adres) {
        userService.saveAddress(adres);

        return "redirect:/user/add";
    }

    @PostMapping("/user/save")
    public String saveUser(UserSaveDto userSaveDto, RedirectAttributes redirectAttributes) {
        var details = UserDetails.builder()
                .taxNumber(userSaveDto.getTaxNumber())
                .personalNumber(userSaveDto.getPersonalNumber())
                .build();
        var phones = new ArrayList<PhoneNumber>();
        for (int i = 0; i < userSaveDto.getPrefix().size(); i++) {
            var phoneNumber = PhoneNumber.builder()
                    .prefix(userSaveDto.getPrefix().get(i))
                    .number(userSaveDto.getNumber().get(i))
                    .build();
            phones.add(phoneNumber);
        }
        var addresses = List.of(
                Address.builder()
                        .addressId(userSaveDto.getAddress())
                        .build());
        var user = User.builder()
                .firstName(userSaveDto.getFirstName())
                .lastName(userSaveDto.getLastName())
                .email(userSaveDto.getEmail())
                .password("secretPass")
                .details(details)
                .phoneNumber(phones)
                .address(addresses)
                .build();

        userService.saveUser(user);
        return "redirect:/user/all";

    }

    @GetMapping("/user/update/id/{userId}")
    public String userUpdate(@PathVariable Integer userId, Model model, UserSaveDto userSaveDto) {
        var optionalUser = userService.getUser(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
        }
        model.addAttribute("addresses", userService.getAddresses());
        return "user/save-user";
    }

}
