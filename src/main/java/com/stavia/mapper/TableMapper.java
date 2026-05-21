package com.stavia.mapper;

import com.stavia.dto.table.TableReservationResponseDto;
import com.stavia.dto.table.TableResponseDto;
import com.stavia.entity.RestaurantTable;
import com.stavia.entity.TableReservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TableMapper {

    TableResponseDto toResponseDto(RestaurantTable table);

    @Mapping(target = "tableNumber", source = "table.tableNumber")
    @Mapping(target = "status", expression = "java(r.getStatus().name())")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "reservationDate", expression = "java(r.getReservationDate() != null ? r.getReservationDate().toString() : null)")
    @Mapping(target = "reservationTime", expression = "java(r.getReservationTime() != null ? r.getReservationTime().toString() : null)")
    @Mapping(target = "createdAt", expression = "java(r.getCreatedAt() != null ? r.getCreatedAt().toString() : null)")
    TableReservationResponseDto toReservationResponseDto(TableReservation r);
}