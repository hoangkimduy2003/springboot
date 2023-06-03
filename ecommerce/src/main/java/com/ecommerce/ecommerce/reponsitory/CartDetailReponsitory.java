package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailReponsitory extends JpaRepository<CartDetail,Long> {
}
