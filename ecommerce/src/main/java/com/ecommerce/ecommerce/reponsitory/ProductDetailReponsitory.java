package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailReponsitory extends JpaRepository<ProductDetail,Long> {
}
