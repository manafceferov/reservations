package com.stavia.repository;

import com.stavia.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findAllByDeletedFalse();
    List<RestaurantTable> findAllByAvailableTrueAndDeletedFalse();

    @Query("""
        SELECT t FROM RestaurantTable t WHERE t.deleted = false
        AND t.available = true
        AND t.id NOT IN (
            SELECT tr.table.id FROM TableReservation tr
            WHERE tr.deleted = false
            AND tr.status NOT IN ('CANCELLED')
            AND tr.reservationDate = :date
            AND tr.reservationTime = :time
        )
    """)
    List<RestaurantTable> findAvailableTables(@Param("date") LocalDate date,
                                              @Param("time") LocalTime time);
}