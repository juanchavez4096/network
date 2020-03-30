package com.message.system.network.controller;

import com.message.system.network.document.User;
import com.message.system.network.dto.UserDTO;
import com.message.system.network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("currentUser")
    public Mono<ResponseEntity<User>> currentUser(@RequestParam(value = "userId") String userId){
        return userRepository.findById(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ResponseEntity<User>> login(@RequestBody UserDTO userDTO){

        if (userDTO.getName() == null){
            return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }

        return userRepository.findByName(userDTO.getName())
                .map(ResponseEntity::ok)
                .switchIfEmpty(
                        userRepository.save(register(userDTO.getName()))
                                .map(ResponseEntity::ok)
                );

    }

    private User register(String username){
        User user = new User();
        user.setName(username);
        user.setCreationDate(new Date());
        user.setLastSignIn(new Date());
        return user;
    }
}
