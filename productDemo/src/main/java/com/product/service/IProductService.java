package com.product.service;

import com.product.dto.ProductDTO;
import com.product.entity.Product;

import java.util.List;

public interface IProductService {
    ProductDTO convertToDTO(Product product);
    Product convertToEntity(ProductDTO productDTO);
    List<ProductDTO> getAll();
    void create(ProductDTO productDTO);
    void delete(Integer id);
    void update(ProductDTO productDTO);
}
