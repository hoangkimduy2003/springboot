package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductReponsitory extends JpaRepository<Product,Long> {

//    @Query("select new com.ecommerce.ecommerce.dto.ProductDTO(p.id,p.name,sum(detail.quantity),sum(detail.quantitySold), )" +
//            " from Product p join p.productDetails detail "+
//            " group by detail.product.id")

}
