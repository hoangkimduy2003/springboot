package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductReponsitory extends JpaRepository<Product,Long> {
    @Query("select p from Product p order by p.totalQuantitySold desc ")
    Page<Product> getBanChay(Pageable pageable);

    @Query("select p from Product p order by p.createdAt desc ")
    Page<Product> getNew(Pageable pageable);

}
