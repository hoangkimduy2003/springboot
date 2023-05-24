package com.product.service.iplm;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.reponsitory.ProductReponsitory;
import com.product.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductReponsitory productRepo;

    @Override
    public ProductDTO convertToDTO(Product product) {
        return new ModelMapper().map(product,ProductDTO.class);
    }

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        return new ModelMapper().map(productDTO,Product.class);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepo.findAll().stream().map(u -> convertToDTO(u)).collect(Collectors.toList());
    }

    @Override
    public void create(ProductDTO productDTO) {
        productRepo.save(convertToEntity(productDTO));
    }

    @Override
    public void delete(Integer id) {
        productRepo.deleteById(id);
    }

    @Override
    public void update(ProductDTO productDTO) {
        Product productEntity = productRepo.findById(productDTO.getId()).orElse(null);
        if(productEntity != null){
            productEntity = convertToEntity(productDTO);
        }
        productRepo.save(productEntity);
    }
}
