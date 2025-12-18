package io.github.aplaraujo.services.impl;

import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.services.models.dto.LoginDTO;
import io.github.aplaraujo.services.models.dto.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {
    public HashMap<String, String> login(LoginDTO dto) throws Exception;
    public ResponseDTO register(User user) throws Exception;
}
