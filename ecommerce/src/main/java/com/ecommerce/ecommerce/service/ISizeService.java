package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.SizeDTO;
import com.ecommerce.ecommerce.entity.Size;
import com.ecommerce.ecommerce.reponsitory.SizeReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ISizeService {

    SizeDTO convertToDto(Size size);

    Size convertToEntity(SizeDTO sizeDTO);

    List<SizeDTO> getAll();

    SizeDTO getById(Long id);

    SizeDTO create(SizeDTO sizeDTO);

    SizeDTO update(SizeDTO sizeDTO);

    void delete(Long id);

    @Service
    class SizeService implements ISizeService {

        @Autowired
        private SizeReponsitory sizeRepo;

        @Override
        public SizeDTO convertToDto(Size size) {
            return new ModelMapper().map(size, SizeDTO.class);
        }

        @Override
        public Size convertToEntity(SizeDTO sizeDTO) {
            return new ModelMapper().map(sizeDTO, Size.class);
        }

        @Override
        public List<SizeDTO> getAll() {
            return sizeRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public SizeDTO getById(Long id) {
            return convertToDto(sizeRepo.findById(id).orElse(null));
        }

        @Override
        public SizeDTO create(SizeDTO sizeDTO) {
            sizeRepo.save(convertToEntity(sizeDTO));
            return sizeDTO;
        }

        @Override
        public SizeDTO update(SizeDTO sizeDTO) {
            Size size = sizeRepo.findById(sizeDTO.getId()).orElse(null);
            if (size != null) {
                size = convertToEntity(sizeDTO);
            }
            sizeRepo.save(size);
            return sizeDTO;
        }

        @Override
        public void delete(Long id) {
            sizeRepo.deleteById(id);
        }
    }
}
