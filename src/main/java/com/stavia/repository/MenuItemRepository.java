package com.stavia.repository;

import com.stavia.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Page<MenuItem> findAllByDeletedFalse(Pageable pageable);
    List<MenuItem> findAllByAvailableTrueAndDeletedFalse();
    List<MenuItem> findAllByCategoryAndAvailableTrueAndDeletedFalse(String category);
}