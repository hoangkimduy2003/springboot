package com.mini.reponsitory;

import com.mini.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReponsitory extends JpaRepository<Category,Long> {
}
