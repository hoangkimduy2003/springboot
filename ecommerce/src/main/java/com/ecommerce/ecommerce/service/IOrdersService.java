package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.CartDetailDTO;
import com.ecommerce.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.ecommerce.dto.OrdersDTO;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.CartDetail;
import com.ecommerce.ecommerce.entity.OrderDetail;
import com.ecommerce.ecommerce.entity.Orders;
import com.ecommerce.ecommerce.reponsitory.CartReponsitory;
import com.ecommerce.ecommerce.reponsitory.OrdersReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface IOrdersService {
    OrdersDTO convertToDto(Orders orders);

    Orders convertToEntity(OrdersDTO ordersDTO);

    List<OrdersDTO> getAll();

    OrdersDTO getById(Long id);

    OrdersDTO create(OrdersDTO ordersDTO);

    OrdersDTO update(OrdersDTO ordersDTO);

    void delete(Long id);

    @Service
    class OrdersService implements IOrdersService {

        @Autowired
        private OrdersReponsitory ordersRepo;

        @Autowired
        private ICartService cartService;

        @Override
        public OrdersDTO convertToDto(Orders orders) {
            return new ModelMapper().map(orders, OrdersDTO.class);
        }

        @Override
        public Orders convertToEntity(OrdersDTO sizeDTO) {
            return new ModelMapper().map(sizeDTO, Orders.class);
        }

        @Override
        public List<OrdersDTO> getAll() {
            return ordersRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public OrdersDTO getById(Long id) {
            return convertToDto(ordersRepo.findById(id).orElse(null));
        }

        @Override
        public OrdersDTO create(OrdersDTO ordersDTO) {
            List<CartDetailDTO> cartDetailDtos = cartService.getByUser(ordersDTO.getUser().getId()).getCartDetails();

            //convert from cartDetail to orderDetail
            List<OrderDetailDTO> orderDetails = cartDetailDtos
                    .stream().map(u -> new ModelMapper().map(u, OrderDetailDTO.class))
                    .collect(Collectors.toList());
            //save to order

            ordersDTO.setOrderDetails(orderDetails);
            ordersRepo.save(convertToEntity(ordersDTO));
            return ordersDTO;
        }

        @Override
        public OrdersDTO update(OrdersDTO ordersDTO) {
            Orders orders = ordersRepo.findById(ordersDTO.getId()).orElse(null);
            if (orders != null) {
                orders = convertToEntity(ordersDTO);
            }
            ordersRepo.save(orders);
            return ordersDTO;
        }

        @Override
        public void delete(Long id) {
            ordersRepo.deleteById(id);
        }
    }
}
