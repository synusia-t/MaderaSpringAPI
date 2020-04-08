package com;

import com.model.UserEntity;
import com.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.Optional;

@Component
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<UserEntity> addNewUser (@RequestParam String firstName,
                                                  @RequestParam String lastName,
                                                  @RequestParam String email,
                                                  @RequestParam String password
    ) {

        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password); //oui, en clair, Hash à utiliser en vrai projet
        userRepository.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(user);

    }

    @GetMapping("/users")
    public @ResponseBody Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public @ResponseBody Optional<UserEntity> getUser(@PathVariable int id) {
        return  userRepository.findById(id);
    }


}
