package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.ColorDTO;
import com.ecommerce.ecommerce.entity.Color;
import com.ecommerce.ecommerce.reponsitory.ColorReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IColorService {
    ColorDTO convertToDto(Color color);

    Color convertToEntity(ColorDTO colorDTO);

    List<ColorDTO> getAll();



    ColorDTO getById(Long id);

    ColorDTO create(ColorDTO colorDTO);

    ColorDTO update(ColorDTO colorDTO);

    void delete(Long id);

    @Service
    class ColorService implements IColorService {

        @Autowired
        private ColorReponsitory colorRepo;

        @Override
        public ColorDTO convertToDto(Color color) {
            return new ModelMapper().map(color, ColorDTO.class);
        }

        @Override
        public Color convertToEntity(ColorDTO colorDTO) {
            return new ModelMapper().map(colorDTO, Color.class);
        }

        @Override
        public List<ColorDTO> getAll() {
            return colorRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }



        @Override
        public ColorDTO getById(Long id) {
            return convertToDto(colorRepo.findById(id).orElse(null));
        }

        @Override
        public ColorDTO create(ColorDTO colorDTO) {
            colorRepo.save(convertToEntity(colorDTO));
            return colorDTO;
        }

        @Override
        public ColorDTO update(ColorDTO colorDTO) {
            Color size = colorRepo.findById(colorDTO.getId()).orElse(null);
            if (size != null) {
                size = convertToEntity(colorDTO);
            }
            colorRepo.save(size);
            return colorDTO;
        }

        @Override
        public void delete(Long id) {
            colorRepo.deleteById(id);
        }
    }

}
