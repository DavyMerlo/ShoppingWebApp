package com.davy.restapi.user.repository;

import com.davy.restapi.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public User findUserByIdWhereDeleteAtIsNull(Long id) {
        return null;
    }
}
