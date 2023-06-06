package com.mini.service;

import com.mini.dto.BrandDTO;
import com.mini.entity.Brand;
import com.mini.reponsitory.BrandReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IBrandService {

    BrandDTO convertToDto(Brand brand);
    Brand converToEntity(BrandDTO brandDTO);
    List<BrandDTO> getAll();
    BrandDTO getById(Long id);
    void create(BrandDTO brandDTO);
    void update(BrandDTO brandDTO);
    void delete(Long id);

    @Service
    class BrandService implements IBrandService{
        @Autowired
        private BrandReponsitory brandRepon;

        @Override
        public BrandDTO convertToDto(Brand brand) {
            return new ModelMapper().map(brand,BrandDTO.class);
        }

        @Override
        public Brand converToEntity(BrandDTO brandDTO) {
            return new ModelMapper().map(brandDTO,Brand.class);
        }

        @Override
        public List<BrandDTO> getAll() {
            return brandRepon.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public BrandDTO getById(Long id) {
            return convertToDto(brandRepon.findById(id).orElse(null));
        }

        @Override
        public void create(BrandDTO brandDTO) {
            brandRepon.save(converToEntity(brandDTO));
        }

        @Override
        public void update(BrandDTO brandDTO) {
            Brand brand = brandRepon.findById(brandDTO.getId()).orElse(null);
            if(brand != null){
                brand = converToEntity(brandDTO);
            }
            brandRepon.save(brand);
        }

        @Override
        public void delete(Long id) {
            brandRepon.deleteById(id);
        }
    }
}
