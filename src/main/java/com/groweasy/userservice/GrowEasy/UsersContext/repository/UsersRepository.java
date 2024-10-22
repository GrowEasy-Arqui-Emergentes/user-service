package com.groweasy.userservice.GrowEasy.UsersContext.repository;

import com.groweasy.userservice.GrowEasy.UsersContext.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> existsUser(@Param("email") String email, @Param("password") String password);

    User findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}
