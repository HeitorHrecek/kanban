package com.example.kanban.controller;

import com.example.kanban.model.User;
import com.example.kanban.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping()
    public User register(@RequestBody User novoUser) {
        return this.service.register(novoUser);
    }

}
