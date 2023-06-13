package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CartDetailReponsitory extends JpaRepository<CartDetail,Long> {
    @Modifying
    @Query("DELETE from CartDetail c where c.cart.id = :idCart")
    void deleteByCart(@Param("idCart") Long id);
}
