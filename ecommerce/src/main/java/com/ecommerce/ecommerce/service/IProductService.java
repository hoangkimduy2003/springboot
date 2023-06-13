package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.PageDTO;
import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.ProductDetailDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.reponsitory.ProductDetailReponsitory;
import com.ecommerce.ecommerce.reponsitory.ProductReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IProductService {
    ProductDTO convertToDto(Product product);

    Product convertToEntity(ProductDTO productDTO);

    List<ProductDTO> getAll();

    PageDTO<List<ProductDTO>> getBanChay(int index, int size);

    PageDTO<List<ProductDTO>> getNew(int index,int size);

    ProductDTO getById(Long id);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO);

    void delete(Long id);

    @Service
    class ProductService implements IProductService {

        @Autowired
        private ProductReponsitory productRepo;

        @Autowired
        private ProductDetailReponsitory productDetailRepo;

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
        public PageDTO<List<ProductDTO>> getBanChay(int index, int size) {
            Page<Product> page = productRepo.getBanChay(PageRequest.of(index,size));
            List<ProductDTO> list = page.get().map(u->convertToDto(u)).collect(Collectors.toList());
            return PageDTO.<List<ProductDTO>>builder()
                    .data(list)
                    .totalElements(page.getTotalElements())
                    .totalpages(page.getTotalPages())
                    .build();
        }

        @Override
        public PageDTO<List<ProductDTO>> getNew(int index,int size) {
            Page<Product> page = productRepo.getNew(PageRequest.of(index,size));
            List<ProductDTO> list = page.get().map(u->convertToDto(u)).collect(Collectors.toList());
            return PageDTO.<List<ProductDTO>>builder()
                    .data(list)
                    .totalElements(page.getTotalElements())
                    .totalpages(page.getTotalPages())
                    .build();
        }

        @Override
        public ProductDTO getById(Long id) {
            return convertToDto(productRepo.findById(id).orElse(null));
        }

        @Override
        public ProductDTO create(ProductDTO productDTO) {
            for (ProductDetailDTO x: productDTO.getProductDetails()){
                x.setQuantitySold(0L);
            }
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
