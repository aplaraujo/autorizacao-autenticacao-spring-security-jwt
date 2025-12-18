package io.github.aplaraujo.services.impl;

import io.github.aplaraujo.services.models.dto.LoginDTO;

import java.util.HashMap;

public interface IAuthService {
    public HashMap<String, String> login(LoginDTO dto) throws Exception;
}
