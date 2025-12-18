package io.github.aplaraujo.services.impl;

import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.repositories.UserRepository;
import io.github.aplaraujo.services.models.dto.LoginDTO;
import io.github.aplaraujo.services.models.dto.ResponseDTO;
import io.github.aplaraujo.services.models.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final IJWTUtilityService jwtUtilityService;
    private final UserValidator userValidator;

    @Override
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

    @Override
    public ResponseDTO register(User user) throws Exception {
        try {
            ResponseDTO response = userValidator.validate(user);

            if (response.getNumOfErrors() > 0) {
                return response;
            }

            List<User> getAllUsers = userRepository.findAll();

            for(User existingUser:getAllUsers) {
                if (existingUser.getEmail().equals(user.getEmail())) {
                    response.setMessage("This email already exists!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("User created successfully!");
            return response;

        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
