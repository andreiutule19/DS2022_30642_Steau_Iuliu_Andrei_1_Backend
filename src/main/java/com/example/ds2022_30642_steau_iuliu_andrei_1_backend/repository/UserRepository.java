package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository;


import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    User save(User user);

    @Override
    List<User> findAll();

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUserId(Long userId);

    void delete(User user);
}
