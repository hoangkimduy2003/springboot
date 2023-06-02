package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReponsitory extends JpaRepository<Product,Long> {
}
