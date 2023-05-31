package com.project_mini.reponsitory;

import com.project_mini.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReponsity extends JpaRepository<Product, Integer> {
}
