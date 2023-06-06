package com.mini.reponsitory;

import com.mini.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductReponsitory extends JpaRepository<Product, Long> {

    @Query("select p from Product p join p.brand b where b.id = :brandId")
    Page<Product> getByBrand(@Param("brandId") Long id, Pageable pageable);

    @Query("select p from Product p join p.status s where s.id = :statusId")
    Page<Product> getByStatus(@Param("statusId") Long id, Pageable pageable);

    @Query("select p from Product p join p.category c where c.id = :categoryId")
    Page<Product> getByCategory(@Param("categoryId") Long id, Pageable pageable);

    @Query("select p from Product p where p.name like %:name%")
    Page<Product> getByName(@Param("name") String name, Pageable pageable);

    @Query("select p from  Product p where p.name like :name " +
            "and (:brandId is null or p.brand.id = :brandId) " +
            "and(:statusId is null or p.status.id = :statusId) " +
            "and (:categoryId is null or p.category.id = :categoryId)")
    Page<Product> search(@Param("name") String name,
                         @Param("brandId") Long brandId,
                         @Param("statusId") Long statusId,
                         @Param("categoryId") Long categoryId,
                         Pageable pageable);

}
