package com.stavia.service;

import com.stavia.dto.auth.LoginRequestDto;
import com.stavia.dto.auth.LoginResponseDto;
import com.stavia.entity.User;
import com.stavia.exception.ResourceNotFoundException;
import com.stavia.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        if (email == null || password == null)
            throw new ResourceNotFoundException("Email və ya şifrə boş ola bilməz");

        User user = userService.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ResourceNotFoundException("Email və ya şifrə yanlışdır");

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name(), user.getId());

        LoginResponseDto response = new LoginResponseDto();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }
}