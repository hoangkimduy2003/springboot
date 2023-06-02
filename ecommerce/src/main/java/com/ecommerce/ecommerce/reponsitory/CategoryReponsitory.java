package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReponsitory extends JpaRepository<Category,Long> {
}
