package com.stavia.repository;

import com.stavia.entity.RoomReservation;
import com.stavia.enums.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
    Page<RoomReservation> findAllByUserIdAndDeletedFalse(Long userId, Pageable pageable);
    Page<RoomReservation> findAllByDeletedFalse(Pageable pageable);
    List<RoomReservation> findAllByStatusAndDeletedFalse(ReservationStatus status);
}