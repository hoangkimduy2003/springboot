package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartReponsitory extends JpaRepository<Cart,Long> {
}
