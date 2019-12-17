package com.full.circle.registration.restjwtpostgres.repository;

import com.full.circle.registration.restjwtpostgres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(name = "select us from usr us where UPPER(us.email) = UPPER(?1) or UPPER(us.user_name) = UPPER(?2)", nativeQuery = true)
    List<User> findByEmailIgnoreCaseOrUserNameIgnoreCase(String email, String username);

    User findByEmail(String email);

    Optional<User> findByTokenConfirmEmail(String token);
}
