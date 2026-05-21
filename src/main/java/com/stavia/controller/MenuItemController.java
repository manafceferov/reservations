package com.stavia.controller;

import com.stavia.config.ApiResponse;
import com.stavia.dto.menuitem.MenuItemCreateDto;
import com.stavia.dto.menuitem.MenuItemResponseDto;
import com.stavia.enums.Messages;
import com.stavia.service.MenuItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public ApiResponse<MenuItemResponseDto> create(@RequestBody MenuItemCreateDto dto) {
        return new ApiResponse<>(true, menuItemService.create(dto), Messages.CREATED.name());
    }

    @GetMapping
    public ApiResponse<Page<MenuItemResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, menuItemService.getAll(PageRequest.of(page, size)), Messages.SUCCESS.name());
    }

    @GetMapping("/category/{category}")
    public ApiResponse<List<MenuItemResponseDto>> getByCategory(@PathVariable String category) {
        return new ApiResponse<>(true, menuItemService.getByCategory(category), Messages.SUCCESS.name());
    }

    @PutMapping("/{id}")
    public ApiResponse<MenuItemResponseDto> edit(@PathVariable Long id,
                                                 @RequestBody MenuItemCreateDto dto) {
        return new ApiResponse<>(true, menuItemService.edit(id, dto), Messages.UPDATED.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        menuItemService.delete(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }
}