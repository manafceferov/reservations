package com.stavia.repository;

import com.stavia.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByTableReservationUserIdAndDeletedFalse(Long userId, Pageable pageable);
    Page<Order> findAllByDeletedFalse(Pageable pageable);
}