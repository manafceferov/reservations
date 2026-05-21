package com.stavia.controller;

import com.stavia.config.ApiResponse;
import com.stavia.dto.room.*;
import com.stavia.enums.Messages;
import com.stavia.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ApiResponse<RoomResponseDto> create(@RequestBody RoomCreateDto dto) {
        return new ApiResponse<>(true, roomService.create(dto), Messages.CREATED.name());
    }

    @GetMapping
    public ApiResponse<Page<RoomResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, roomService.getAll(PageRequest.of(page, size)), Messages.SUCCESS.name());
    }

    @GetMapping("/available")
    public ApiResponse<List<RoomResponseDto>> getAvailable(@RequestParam String checkIn,
                                                           @RequestParam String checkOut) {
        return new ApiResponse<>(true, roomService.getAvailable(checkIn, checkOut), Messages.SUCCESS.name());
    }

    @PutMapping("/{id}")
    public ApiResponse<RoomResponseDto> edit(@PathVariable Long id,
                                             @RequestBody RoomEditDto dto) {
        return new ApiResponse<>(true, roomService.edit(id, dto), Messages.UPDATED.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }

    @PostMapping("/reservations")
    public ApiResponse<RoomReservationResponseDto> reserve(@RequestBody RoomReservationCreateDto dto,
                                                           @RequestParam Long userId) {
        return new ApiResponse<>(true, roomService.reserve(dto, userId), Messages.RESERVATION_CONFIRMED.name());
    }

    @GetMapping("/reservations/my")
    public ApiResponse<Page<RoomReservationResponseDto>> myReservations(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, roomService.getUserReservations(userId, PageRequest.of(page, size)), Messages.SUCCESS.name());
    }

    @GetMapping("/reservations")
    public ApiResponse<Page<RoomReservationResponseDto>> allReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, roomService.getAllReservations(PageRequest.of(page, size)), Messages.SUCCESS.name());
    }

    @PutMapping("/reservations/{id}/cancel")
    public ApiResponse<RoomReservationResponseDto> cancel(@PathVariable Long id,
                                                          @RequestParam Long userId) {
        return new ApiResponse<>(true, roomService.cancelReservation(id, userId), Messages.RESERVATION_CANCELLED.name());
    }

    @PutMapping("/reservations/{id}/checkin")
    public ApiResponse<RoomReservationResponseDto> checkIn(@PathVariable Long id) {
        return new ApiResponse<>(true, roomService.checkIn(id), Messages.CHECKED_IN.name());
    }

    @PutMapping("/reservations/{id}/checkout")
    public ApiResponse<RoomReservationResponseDto> checkOut(@PathVariable Long id) {
        return new ApiResponse<>(true, roomService.checkOut(id), Messages.CHECKED_OUT.name());
    }
}