package com.message.system.network.controller;

import com.message.system.network.document.Users;
import com.message.system.network.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value="currentUser", method= RequestMethod.GET)
    public Mono<ResponseEntity<Users>> currentUser() {

        return usersRepository.findById("")
                .map(ResponseEntity::ok);
    }
}
