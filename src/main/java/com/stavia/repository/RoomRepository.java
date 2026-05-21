package com.stavia.repository;

import com.stavia.entity.Room;
import com.stavia.enums.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findAllByDeletedFalse(Pageable pageable);
    List<Room> findAllByAvailableTrueAndDeletedFalse();

    @Query("""
        SELECT r FROM Room r WHERE r.deleted = false
        AND r.available = true
        AND r.id NOT IN (
            SELECT rr.room.id FROM RoomReservation rr
            WHERE rr.deleted = false
            AND rr.status NOT IN ('CANCELLED', 'CHECKED_OUT')
            AND rr.checkIn < :checkOut
            AND rr.checkOut > :checkIn
        )
    """)
    List<Room> findAvailableRooms(@Param("checkIn") LocalDate checkIn,
                                  @Param("checkOut") LocalDate checkOut);
}