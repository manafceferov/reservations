package com.stavia.mapper;

import com.stavia.dto.menuitem.MenuItemResponseDto;
import com.stavia.entity.MenuItem;
import org.mapstruct.Mapper;

@Mapper
public interface MenuItemMapper {
    MenuItemResponseDto toResponseDto(MenuItem item);
}