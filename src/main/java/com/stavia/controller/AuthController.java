package com.stavia.controller;

import com.stavia.config.ApiResponse;
import com.stavia.dto.auth.LoginRequestDto;
import com.stavia.dto.auth.LoginResponseDto;
import com.stavia.dto.user.UserRegisterDto;
import com.stavia.dto.user.UserResponseDto;
import com.stavia.enums.Messages;
import com.stavia.service.AuthService;
import com.stavia.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService,
                          UserService userService
    ) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponseDto> register(@RequestBody UserRegisterDto dto) {
        return new ApiResponse<>(true, userService.register(dto), Messages.CREATED.name());
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return new ApiResponse<>(true, authService.login(dto), Messages.SUCCESS.name());
    }
}