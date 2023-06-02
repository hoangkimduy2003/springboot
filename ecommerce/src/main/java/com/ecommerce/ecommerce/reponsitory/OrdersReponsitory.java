package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersReponsitory extends JpaRepository<Orders,Long> {
}
