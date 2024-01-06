package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Address;

import com.example.demo.models.User;
import com.example.demo.repositories.AdressRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    // Konstruktor z pól finalnych
    final UserRepository userRepository;
    final AdressRepository adressRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public void saveAddress(@Valid Address adres) {
        adressRepository.save(adres);
    }

    public List<Address> getAddresses() {
        return adressRepository.findAll();
    }

    public void saveUser(@Valid User user) {
        user.setUserId(null);
        userRepository.save(user);
    }

    public void removeUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
