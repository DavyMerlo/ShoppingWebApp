package com.davy.restapi.user.repository;

import com.davy.restapi.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>,
        CustomUserRepository {
    Optional<UserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u " +
            "SET u.enabled = TRUE WHERE u.email = ?1")
    int enableAppUser(String email);
}
