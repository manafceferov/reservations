package com.stavia.controller;

import com.stavia.config.ApiResponse;
import com.stavia.dto.user.UserEditDto;
import com.stavia.dto.user.UserResponseDto;
import com.stavia.enums.Messages;
import com.stavia.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponseDto> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, userService.getById(id), Messages.SUCCESS.name());
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponseDto> edit(@PathVariable Long id, @RequestBody UserEditDto dto) {
        return new ApiResponse<>(true, userService.edit(id, dto), Messages.UPDATED.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }
}