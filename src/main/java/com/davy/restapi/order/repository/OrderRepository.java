package com.davy.restapi.order.repository;

import com.davy.restapi.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>,
        CustomOrderRepository{

    Page<OrderEntity> findAll(@Nullable Specification<OrderEntity> spec, @NonNull Pageable pageable);
}
