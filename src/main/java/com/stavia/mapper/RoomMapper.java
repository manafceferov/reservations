package com.stavia.mapper;

import com.stavia.dto.room.RoomReservationResponseDto;
import com.stavia.dto.room.RoomResponseDto;
import com.stavia.entity.Room;
import com.stavia.entity.RoomReservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RoomMapper {

    @Mapping(target = "roomType", expression = "java(room.getRoomType().name())")
    RoomResponseDto toResponseDto(Room room);

    @Mapping(target = "roomNumber", source = "room.roomNumber")
    @Mapping(target = "roomType", expression = "java(r.getRoom().getRoomType().name())")
    @Mapping(target = "status", expression = "java(r.getStatus().name())")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "checkIn", expression = "java(r.getCheckIn() != null ? r.getCheckIn().toString() : null)")
    @Mapping(target = "checkOut", expression = "java(r.getCheckOut() != null ? r.getCheckOut().toString() : null)")
    @Mapping(target = "createdAt", expression = "java(r.getCreatedAt() != null ? r.getCreatedAt().toString() : null)")
    RoomReservationResponseDto toReservationResponseDto(RoomReservation r);
}