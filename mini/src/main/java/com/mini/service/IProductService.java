package com.mini.service;

import com.mini.dto.PageDTO;
import com.mini.dto.ProductDTO;
import com.mini.dto.SearchDTO;
import com.mini.entity.Product;
import com.mini.reponsitory.ProductReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface IProductService {
    ProductDTO convertToDto(Product product);

    Product convertToEntity(ProductDTO productDTO);

    PageDTO<List<ProductDTO>> search(SearchDTO searchDTO);

    ProductDTO getById(Long id);

    void create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

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
        public PageDTO<List<ProductDTO>> search(SearchDTO searchDTO) {
            searchDTO.setName(searchDTO.getName() == null ? "" : searchDTO.getName());
            searchDTO.setPageIndex(searchDTO.getPageIndex() == null ? 0 : searchDTO.getPageIndex());
            searchDTO.setPageSize(searchDTO.getPageSize() == null ? 10 : searchDTO.getPageSize());
            Page<Product> pageEntity = productRepo.search(
                    "%" + searchDTO.getName() + "%",
                     searchDTO.getBrandId(),
                    searchDTO.getStatusId(),
                    searchDTO.getCategoryId(),
                    PageRequest.of(searchDTO.getPageIndex(), searchDTO.getPageSize())
            );
            List<ProductDTO> list = pageEntity.get().map(u -> convertToDto(u)).collect(Collectors.toList());

            return PageDTO
                    .<List<ProductDTO>>builder()
                    .data(list)
                    .totalElements(pageEntity.getTotalElements())
                    .totalPages(pageEntity.getTotalPages())
                    .build();
        }

        @Override
        public ProductDTO getById(Long id) {
            return convertToDto(productRepo.findById(id).orElse(null));
        }

        @Override
        public void create(ProductDTO productDTO) {
            productRepo.save(convertToEntity(productDTO));
        }

        @Override
        public void update(ProductDTO productDTO) {
            Product product = productRepo.findById(productDTO.getId()).orElse(null);
            if (product != null) {
                product = convertToEntity(productDTO);
            }
            productRepo.save(product);
        }

        @Override
        public void delete(Long id) {
            productRepo.deleteById(id);
        }
    }
}
