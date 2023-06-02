package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.reponsitory.ProductReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IProductService {
    ProductDTO convertToDto(Product product);

    Product convertToEntity(ProductDTO productDTO);

    List<ProductDTO> getAll();

    ProductDTO getById(Long id);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO);

    void delete(Long id);

    @Service
    class ProductService implements IProductService {

        @Autowired
        private ProductReponsitory productRepo;

        @Override
        public ProductDTO convertToDto(Product product) {
            return new ModelMapper().map(product, ProductDTO.class);
        }

        @Override
        public Product convertToEntity(ProductDTO productDTO) {
            return new ModelMapper().map(productDTO, Product.class);
        }

        @Override
        public List<ProductDTO> getAll() {
            return productRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public ProductDTO getById(Long id) {
            return convertToDto(productRepo.findById(id).orElse(null));
        }

        @Override
        public ProductDTO create(ProductDTO productDTO) {
            productRepo.save(convertToEntity(productDTO));
            return productDTO;
        }

        @Override
        public ProductDTO update(ProductDTO productDTO) {
            Product product = productRepo.findById(productDTO.getId()).orElse(null);
            if (product != null) {
                product = convertToEntity(productDTO);
            }
            productRepo.save(product);
            return productDTO;
        }

        @Override
        public void delete(Long id) {
            productRepo.deleteById(id);
        }
    }
}
