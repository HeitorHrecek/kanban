package com.example.kanban.service;

import com.example.kanban.controller.dto.AuthRequest;
import com.example.kanban.controller.dto.AuthResponse;
import com.example.kanban.model.User;
import com.example.kanban.service.security.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {


    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthResponse login (AuthRequest dadosAuthRequest) {
        User user = this.userService.consultByUsername(dadosAuthRequest.getUsername());

        if (!user.getPassword().equals(dadosAuthRequest.getPassword())) {
            throw new RuntimeException("Senha incorreta !");
        }

        String token = this.authenticationService.gerarToken(user);
        return new AuthResponse(user.getUsername(), token);
    }
}
