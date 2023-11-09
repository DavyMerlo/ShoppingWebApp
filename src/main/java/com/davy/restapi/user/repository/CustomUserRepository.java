package com.davy.restapi.user.repository;

import com.davy.restapi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository {
    User findUserByIdWhereDeleteAtIsNull(Long id);
}
