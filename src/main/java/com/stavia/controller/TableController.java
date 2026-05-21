package com.stavia.controller;

import com.stavia.config.ApiResponse;
import com.stavia.dto.table.*;
import com.stavia.enums.Messages;
import com.stavia.service.TableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ApiResponse<TableResponseDto> create(@RequestBody TableCreateDto dto) {
        return new ApiResponse<>(true, tableService.create(dto), Messages.CREATED.name());
    }

    @GetMapping
    public ApiResponse<List<TableResponseDto>> getAll() {
        return new ApiResponse<>(true, tableService.getAll(), Messages.SUCCESS.name());
    }

    @GetMapping("/available")
    public ApiResponse<List<TableResponseDto>> getAvailable(@RequestParam String date,
                                                            @RequestParam String time) {
        return new ApiResponse<>(true, tableService.getAvailable(date, time), Messages.SUCCESS.name());
    }

    @PostMapping("/reservations")
    public ApiResponse<TableReservationResponseDto> reserve(@RequestBody TableReservationCreateDto dto,
                                                            @RequestParam Long userId) {
        return new ApiResponse<>(true, tableService.reserve(dto, userId), Messages.RESERVATION_CONFIRMED.name());
    }

    @GetMapping("/reservations/my")
    public ApiResponse<Page<TableReservationResponseDto>> myReservations(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, tableService.getUserReservations(userId, PageRequest.of(page, size)), Messages.SUCCESS.name());
    }

    @GetMapping("/reservations")
    public ApiResponse<Page<TableReservationResponseDto>> allReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, tableService.getAllReservations(PageRequest.of(page, size)), Messages.SUCCESS.name());
    }

    @PutMapping("/reservations/{id}/cancel")
    public ApiResponse<TableReservationResponseDto> cancel(@PathVariable Long id,
                                                           @RequestParam Long userId) {
        return new ApiResponse<>(true, tableService.cancelReservation(id, userId), Messages.RESERVATION_CANCELLED.name());
    }
}