package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailReponsitory extends JpaRepository<OrderDetail,Long> {
}
