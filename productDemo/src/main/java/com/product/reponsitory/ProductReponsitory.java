package com.product.reponsitory;

import com.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReponsitory extends JpaRepository<Product,Integer> {
}
