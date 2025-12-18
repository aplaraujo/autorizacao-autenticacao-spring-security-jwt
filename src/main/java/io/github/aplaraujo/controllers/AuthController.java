package io.github.aplaraujo.controllers;

import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.services.impl.IAuthService;
import io.github.aplaraujo.services.models.dto.LoginDTO;
import io.github.aplaraujo.services.models.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> register(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO dto) throws Exception {
        HashMap<String, String> login = authService.login(dto);
        if (login.containsKey("jwt")) {
            return new ResponseEntity<>(login, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(login, HttpStatus.UNAUTHORIZED);
        }
    }
}
