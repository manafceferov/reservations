package com.stavia.repository;

import com.stavia.entity.TableReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {
    Page<TableReservation> findAllByUserIdAndDeletedFalse(Long userId, Pageable pageable);
    Page<TableReservation> findAllByDeletedFalse(Pageable pageable);
}