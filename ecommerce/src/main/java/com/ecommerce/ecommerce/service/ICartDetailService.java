package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CartDetailDTO;
import com.ecommerce.ecommerce.entity.CartDetail;
import com.ecommerce.ecommerce.reponsitory.CartDetailReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ICartDetailService {
    CartDetailDTO convertToDto(CartDetail cartDetail);

    CartDetail convertToEntity(CartDetailDTO cartDetailDTO);

    List<CartDetailDTO> getAll();

    CartDetailDTO getById(Long id);

    CartDetailDTO create(CartDetailDTO cartDetailDTO);

    CartDetailDTO update(CartDetailDTO cartDetailDTO);

    void delete(Long id);

    @Service
    class CartDetailService implements ICartDetailService {

        @Autowired
        private CartDetailReponsitory cartDetailRepo;

        @Override
        public CartDetailDTO convertToDto(CartDetail cart) {
            return new ModelMapper().map(cart, CartDetailDTO.class);
        }

        @Override
        public CartDetail convertToEntity(CartDetailDTO cartDetailDTO) {
            return new ModelMapper().map(cartDetailDTO, CartDetail.class);
        }

        @Override
        public List<CartDetailDTO> getAll() {
            return cartDetailRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public CartDetailDTO getById(Long id) {
            return convertToDto(cartDetailRepo.findById(id).orElse(null));
        }

        @Override
        public CartDetailDTO create(CartDetailDTO cartDetailDTO) {
            cartDetailRepo.save(convertToEntity(cartDetailDTO));
            return cartDetailDTO;
        }

        @Override
        public CartDetailDTO update(CartDetailDTO cartDetailDTO) {
            CartDetail cartDetail = cartDetailRepo.findById(cartDetailDTO.getId()).orElse(null);
            if (cartDetail != null) {
                cartDetail = convertToEntity(cartDetailDTO);
            }
            cartDetailRepo.save(cartDetail);
            return cartDetailDTO;
        }

        @Override
        public void delete(Long id) {
            long ida = id;
            cartDetailRepo.deleteById(id);
        }
    }
}
