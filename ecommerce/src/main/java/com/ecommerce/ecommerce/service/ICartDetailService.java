package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CartDetailDTO;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.CartDetail;
import com.ecommerce.ecommerce.entity.ProductDetail;
import com.ecommerce.ecommerce.reponsitory.CartDetailReponsitory;
import com.ecommerce.ecommerce.reponsitory.CartReponsitory;
import com.ecommerce.ecommerce.reponsitory.ProductDetailReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public interface ICartDetailService {
    CartDetailDTO convertToDto(CartDetail cartDetail);

    CartDetail convertToEntity(CartDetailDTO cartDetailDTO);

    List<CartDetailDTO> getAll();

    CartDetailDTO getById(Long id);

    CartDetailDTO convertTotal(CartDetailDTO cartDetailDTO, ProductDetail productDetail);

    CartDetailDTO create(CartDetailDTO cartDetailDTO);

    CartDetailDTO update(CartDetailDTO cartDetailDTO);

    void delete(Long id);
    void deleteByCart(Long id);

    @Service
    class CartDetailService implements ICartDetailService {

        @Autowired
        private CartDetailReponsitory cartDetailRepo;

        @Autowired
        private CartReponsitory cartRepo;

        @Autowired
        private ProductDetailReponsitory productDetailRepo;

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
        public CartDetailDTO convertTotal(CartDetailDTO cartDetailDTO, ProductDetail productDetail) {
            cartDetailDTO.setPrice(productDetail.getPrice());
            cartDetailDTO.setTotalMoney(productDetail.getPrice().multiply(new BigDecimal(cartDetailDTO.getQuantity())));
            return cartDetailDTO;
        }

        @Override
        public CartDetailDTO create(CartDetailDTO cartDetailDTO) {
            ProductDetail productDetail = productDetailRepo.findById(cartDetailDTO.getProductDetail().getId()).orElse(null);
            cartDetailDTO = convertTotal(cartDetailDTO, productDetail);

            Cart cart = cartRepo.findById(cartDetailDTO.getCart().getId()).orElse(null);
            cart.setTotalProduct(cart.getTotalProduct() + cartDetailDTO.getQuantity());
            cart.setTotalMoney(cart.getTotalMoney().add(cartDetailDTO.getTotalMoney()));

            cartRepo.save(cart);
            cartDetailRepo.save(convertToEntity(cartDetailDTO));
            return cartDetailDTO;
        }

        @Override
        public CartDetailDTO update(CartDetailDTO cartDetailDTO) {
            ProductDetail productDetail = productDetailRepo.findById(cartDetailDTO.getProductDetail().getId()).orElse(null);
            cartDetailDTO = convertTotal(cartDetailDTO, productDetail);

            CartDetail cartDetail = cartDetailRepo.findById(cartDetailDTO.getId()).orElse(null);
            Cart cart = cartRepo.findById(cartDetailDTO.getCart().getId()).orElse(null);

            cart.setTotalProduct(cart.getTotalProduct() - cartDetail.getQuantity());
            cart.setTotalMoney(cart.getTotalMoney().subtract(cartDetail.getTotalMoney()));

            if (cartDetail != null) {
                cartDetail = convertToEntity(cartDetailDTO);
            }

            cart.setTotalProduct(cart.getTotalProduct() + cartDetailDTO.getQuantity());
            cart.setTotalMoney(cart.getTotalMoney().add(cartDetailDTO.getTotalMoney()));
            cartDetailRepo.save(cartDetail);
            cartRepo.save(cart);
            return cartDetailDTO;
        }

        @Override
        public void delete(Long id) {
            CartDetail cartDetail = cartDetailRepo.findById(id).orElse(null);
            Cart cart = cartRepo.findById(cartDetail.getCart().getId()).orElse(null);
            cart.setTotalProduct(cart.getTotalProduct() - cartDetail.getQuantity());
            cart.setTotalMoney(cart.getTotalMoney().subtract(cartDetail.getTotalMoney()));
            cartRepo.save(cart);
            cartDetailRepo.deleteById(id);
        }

        @Override
        public void deleteByCart(Long id) {
            cartDetailRepo.deleteByCart(id);
        }
    }
}
