package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;


public interface CartReponsitory extends JpaRepository<Cart,Long> {
    @Query("select c from Cart c join c.user u where u.id = :id")
    Cart getByUser(@Param("id")Long id);

}
