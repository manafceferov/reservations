package com.stavia.mapper;

import com.stavia.dto.user.UserResponseDto;
import com.stavia.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "role", expression = "java(user.getRole().name())")
    UserResponseDto toResponseDto(User user);
}