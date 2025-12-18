package io.github.aplaraujo.services.impl;

import io.github.aplaraujo.entities.User;

import java.util.List;

public interface IUserService {
    public List<User> findAllUsers();
}
