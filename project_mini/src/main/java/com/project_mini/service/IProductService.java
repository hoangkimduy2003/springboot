package com.project_mini.service;

import com.project_mini.dto.ProductDTO;
import com.project_mini.entity.Product;
import com.project_mini.reponsitory.ProductReponsity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IProductService {
    ProductDTO convertToDto(Product product);

    Product convertToEntity(ProductDTO productDTO);

    List<ProductDTO> getAll();

    void create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(Integer id);

    ProductDTO getById(Integer id);


    @Service
    class ProductService implements IProductService {

        @Autowired
        private ProductReponsity productRepo;

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
            return productRepo.findAll().stream()
                    .map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public void create(ProductDTO productDTO) {
            productRepo.save(convertToEntity(productDTO));
        }

        @Override
        public void update(ProductDTO productDTO) {
            Product productEntity = productRepo.findById(productDTO.getId()).orElse(null);
            if(productEntity != null){
                productEntity = convertToEntity(productDTO);
            }
            productRepo.save(productEntity);
        }

        @Override
        public void delete(Integer id) {
            productRepo.deleteById(id);
        }

        @Override
        public ProductDTO getById(Integer id) {
            return convertToDto(productRepo.findById(id).orElse(null));
        }
    }
}
