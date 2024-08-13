package com.example.userdept.controllers;

import com.example.userdept.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.userdept.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @PostMapping
    public User insert(@RequestBody User user) {
        User saved = userRepository.save(user);
        return user;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.deleteById(id);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

