
package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Address;
import com.example.demo.models.User;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final AddressRepository addressRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Optional<User> getUser(Integer userId) {
        return userRepository.findById(userId);
    }

    public void saveAddress(@Valid Address address) {
        addressRepository.save(address);
    }

    public void saveUser(User user) {
        user.setUserId(null);
        userRepository.save(user);
    }

    public void updateUser(Integer userId, @Valid User user) {
        user.setUserId(userId);
        userRepository.save(user);
    }

    public void removeUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
