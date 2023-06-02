package com.example.demo.reponsitory;

import com.example.demo.entity.ProductDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDemoRepo  extends JpaRepository<ProductDemo,Long> {
}
