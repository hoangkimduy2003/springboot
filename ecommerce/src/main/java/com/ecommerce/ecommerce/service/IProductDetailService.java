package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.ProductDetailDTO;
import com.ecommerce.ecommerce.entity.ProductDetail;
import com.ecommerce.ecommerce.reponsitory.ProductDetailReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IProductDetailService {

    ProductDetailDTO convertToDto(ProductDetail productDetail);

    ProductDetail convertToEntity(ProductDetailDTO productDetailDTO);

    List<ProductDetailDTO> getAll();

    ProductDetailDTO getById(Long id);

    ProductDetailDTO create(ProductDetailDTO productDetailDTO);

    ProductDetailDTO update(ProductDetailDTO productDetailDTO);

    void delete(Long id);

    @Service
    class ProductDetailService implements IProductDetailService {

        @Autowired
        private ProductDetailReponsitory productDetailRepo;

        @Override
        public ProductDetailDTO convertToDto(ProductDetail productDetail) {
            return new ModelMapper().map(productDetail, ProductDetailDTO.class);
        }

        @Override
        public ProductDetail convertToEntity(ProductDetailDTO productDetailDTO) {
            return new ModelMapper().map(productDetailDTO, ProductDetail.class);
        }

        @Override
        public List<ProductDetailDTO> getAll() {
            return productDetailRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public ProductDetailDTO getById(Long id) {
            return convertToDto(productDetailRepo.findById(id).orElse(null));
        }

        @Override
        public ProductDetailDTO create(ProductDetailDTO productDetailDTO) {
            productDetailRepo.save(convertToEntity(productDetailDTO));
            return productDetailDTO;
        }

        @Override
        public ProductDetailDTO update(ProductDetailDTO productDetailDTO) {
            ProductDetail productDetail = productDetailRepo.findById(productDetailDTO.getId()).orElse(null);
            if (productDetail != null) {
                productDetail = convertToEntity(productDetailDTO);
            }
            productDetailRepo.save(productDetail);
            return productDetailDTO;
        }

        @Override
        public void delete(Long id) {
            productDetailRepo.deleteById(id);
        }
    }
}
