package io.github.aplaraujo.repositories;

import io.github.aplaraujo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
