package com.davy.restapi.order.service;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.user.entity.UserEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class OrderSpecification {

    private OrderSpecification(){}

    public static Specification<OrderEntity> byUser(Long userId){
        return (root, query, builder) -> {
            Join<OrderEntity, UserEntity> userJoin = root.join("user");
            return builder.equal(userJoin.get("id"), userId);
        };
    }
}
