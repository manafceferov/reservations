package com.stavia.service;

import com.stavia.dto.menuitem.MenuItemCreateDto;
import com.stavia.dto.menuitem.MenuItemResponseDto;
import com.stavia.entity.MenuItem;
import com.stavia.exception.ResourceNotFoundException;
import com.stavia.mapper.MenuItemMapper;
import com.stavia.repository.MenuItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemService(MenuItemRepository menuItemRepository,
                           MenuItemMapper menuItemMapper
    ) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Transactional
    public MenuItemResponseDto create(MenuItemCreateDto dto) {
        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCategory(dto.getCategory());
        return menuItemMapper.toResponseDto(menuItemRepository.save(item));
    }

    public Page<MenuItemResponseDto> getAll(Pageable pageable) {
        return menuItemRepository.findAllByDeletedFalse(pageable)
                .map(menuItemMapper::toResponseDto);
    }

    public List<MenuItemResponseDto> getByCategory(String category) {
        return menuItemRepository.findAllByCategoryAndAvailableTrueAndDeletedFalse(category)
                .stream().map(menuItemMapper::toResponseDto).toList();
    }

    @Transactional
    public MenuItemResponseDto edit(Long id, MenuItemCreateDto dto) {
        MenuItem item = findById(id);
        if (dto.getName() != null) item.setName(dto.getName());
        if (dto.getDescription() != null) item.setDescription(dto.getDescription());
        if (dto.getPrice() != null) item.setPrice(dto.getPrice());
        if (dto.getCategory() != null) item.setCategory(dto.getCategory());
        return menuItemMapper.toResponseDto(menuItemRepository.save(item));
    }

    @Transactional
    public void delete(Long id) {
        MenuItem item = findById(id);
        item.setDeleted(true);
        menuItemRepository.save(item);
    }

    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menyu elementi tapılmadı"));
    }
}