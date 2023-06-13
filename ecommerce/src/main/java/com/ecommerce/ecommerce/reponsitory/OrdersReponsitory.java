package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
public interface OrdersReponsitory extends JpaRepository<Orders,Long> {
    @Query("select o from Orders o where o.user.id = :idUser and o.status = :statusOrder")
    List<Orders> getByUserAndStatus(@Param("idUser") Long id,@Param("statusOrder")Integer status);

    @Query("select o from Orders o where o.user.id = :idUser order by o.orderDate desc ")
    List<Orders> getByUser(@Param("idUser") Long id);


    @Modifying
    @Query("update Orders o set o.status = :status where o.id = :id")
    void updateStatus(@Param("status") Integer status, @Param("id") Long id);

    @Query("select o from Orders o order by o.orderDate desc ")
    List<Orders> findAllByOrderDateDesc();
}
