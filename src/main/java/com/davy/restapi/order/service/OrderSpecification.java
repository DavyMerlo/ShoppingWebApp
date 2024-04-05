package com.davy.restapi.order.service;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.user.entity.UserEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class OrderSpecification {

    private OrderSpecification(){}

    public static Specification<OrderEntity> bySearch(String search) {
        return (root, query, builder) -> {
            LocalDateTime dateTime = parseDateTime(search);
            if (dateTime != null) {
                return byDate(dateTime).toPredicate(root, query, builder);
            } else {
                return byStatus(search).toPredicate(root, query, builder);
            }
        };
    }

    private static Specification<OrderEntity> byName(String fullName){
        return (root, query, builder) -> {
            Join<OrderEntity, UserEntity> userJoin = root.join("user");
            String[] names = fullName.split(" ");
            Predicate userFirstNamePredicate = builder.equal(userJoin.get("firstname"), names[0]);
            Predicate userLastNamePredicate = builder.equal(userJoin.get("lastname"), names.length > 1 ? names[1] : "");
            return builder.and(userFirstNamePredicate, userLastNamePredicate);
        };
    }

    public static Specification<OrderEntity> byUserId(Long userId){
        return (root, query, builder) -> {
            Join<OrderEntity, UserEntity> userJoin = root.join("user");
            return builder.equal(userJoin.get("id"), userId);
        };
    }

    public static Specification<OrderEntity> byOrderId(Long orderId) {
        return (root, query, builder) -> builder.equal(root.get("id"), orderId);
    }

    private static Specification<OrderEntity> byDate(LocalDateTime dateTime) {
        LocalDateTime startDateTime = dateTime.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endDateTime = startDateTime.plusMinutes(1);
        return (root, query, builder) ->
                builder.between(root.get("createdAt"), startDateTime, endDateTime);
    }

    private static Specification<OrderEntity> byStatus(String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        return (root, query, builder) -> builder.equal(root.get("status"), orderStatus);
    }

    private static LocalDateTime parseDateTime(String search) {
        try {
            return LocalDateTime.parse(search, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (Exception e) {
            return null;
        }
    }
}
