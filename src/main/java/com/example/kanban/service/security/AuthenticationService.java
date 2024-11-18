package com.example.kanban.service.security;

import com.example.kanban.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationService {

    private final TokenService tokenService;

    public String gerarToken(User user) {
        return tokenService.geradorToken(user.getUsername());
    }

    public String validaToken(String token) {
        return tokenService.validaToken(token);
    }



}
