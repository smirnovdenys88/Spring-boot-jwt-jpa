package com.start.kit.repository;

import com.start.kit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailIgnoreCaseOrUserNameIgnoreCase(String email, String username);

    User findByEmail(String email);

    Optional<User> findByTokenConfirmEmail(String token);
}
