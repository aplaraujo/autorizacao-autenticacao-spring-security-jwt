package io.github.aplaraujo.services.impl;

import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.repositories.UserRepository;
import io.github.aplaraujo.services.models.dto.LoginDTO;
import io.github.aplaraujo.services.models.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final IJWTUtilityService jwtUtilityService;
    private final UserValidator userValidator;

    public HashMap<String, String> login(LoginDTO dto) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<User> user = userRepository.findByEmail(dto.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "User not registered!");
                return jwt;
            }

            // Verificar a senha
            if (verifyPassword(dto.getPassword(), user.get().getPassword())) {
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "Authentication failed!");
            }

            return jwt;

        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
