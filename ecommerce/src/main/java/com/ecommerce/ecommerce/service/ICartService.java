package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.reponsitory.CartReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ICartService {
    CartDTO convertToDto(Cart cart);

    Cart convertToEntity(CartDTO cartDTO);

    List<CartDTO> getAll();

    CartDTO getByUser(Long id);

    CartDTO getById(Long id);

    CartDTO create(CartDTO cartDTO);

    CartDTO update(CartDTO cartDTO);

    void delete(Long id);

    @Service
    class CartService implements ICartService {

        @Autowired
        private CartReponsitory cartRepo;

        @Override
        public CartDTO convertToDto(Cart cart) {
            return new ModelMapper().map(cart, CartDTO.class);
        }

        @Override
        public Cart convertToEntity(CartDTO cartDTO) {
            return new ModelMapper().map(cartDTO, Cart.class);
        }

        @Override
        public List<CartDTO> getAll() {
            return cartRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public CartDTO getByUser(Long id) {
            return convertToDto(cartRepo.getByUser(id));
        }

        @Override
        public CartDTO getById(Long id) {
            return convertToDto(cartRepo.findById(id).orElse(null));
        }

        @Override
        public CartDTO create(CartDTO cartDTO) {
            cartRepo.save(convertToEntity(cartDTO));
            return cartDTO;
        }

        @Override
        public CartDTO update(CartDTO cartDTO) {
            Cart cart = cartRepo.findById(cartDTO.getId()).orElse(null);
            if (cart != null) {
                cart = convertToEntity(cartDTO);
            }
            cartRepo.save(cart);
            return cartDTO;
        }

        @Override
        public void delete(Long id) {
            cartRepo.deleteById(id);
        }
    }
}
