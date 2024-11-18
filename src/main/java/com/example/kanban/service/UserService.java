package com.example.kanban.service;

import com.example.kanban.model.User;
import com.example.kanban.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User register(User user) {
        return this.repository.save(user);
    }

    public User consultByUsername (String username) {
        Optional<User> usuario = this.repository.findByUsername(username);

        if(usuario.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado");
        }

        return usuario.get();
    }
}
