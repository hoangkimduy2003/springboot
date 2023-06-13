package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.*;
import com.ecommerce.ecommerce.entity.*;
import com.ecommerce.ecommerce.reponsitory.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface IOrdersService {
    OrdersDTO convertToDto(Orders orders);

    Orders convertToEntity(OrdersDTO ordersDTO);

    List<OrdersDTO> getAll();

    List<OrdersDTO> getByUserAndStatus(Long id, Integer status);

    List<OrdersDTO> getByUser(Long id);

    void updateStatus(Integer status, Long id);

    OrdersDTO getById(Long id);

    OrdersDTO create(OrdersDTO ordersDTO);

    OrdersDTO update(OrdersDTO ordersDTO);

    void delete(Long id);

    @Service
    class OrdersService implements IOrdersService {

        @Autowired
        private OrdersReponsitory ordersRepo;

        @Autowired
        private CartReponsitory cartRepo;

        @Autowired
        private ProductReponsitory productRepo;

        @Autowired
        private CartDetailReponsitory cartDetailRepo;

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
            return ordersRepo.findAllByOrderDateDesc().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public List<OrdersDTO> getByUserAndStatus(Long id, Integer status) {
            return ordersRepo.getByUserAndStatus(id, status).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public List<OrdersDTO> getByUser(Long id) {
            return ordersRepo.getByUser(id).stream().map(o -> convertToDto(o))
                    .collect(Collectors.toList());
        }

        @Override
        public void updateStatus(Integer status, Long id) {
            if (status == 0) {
                Orders orders = ordersRepo.findById(id).orElse(null);
                if (orders != null) {
                    for (OrderDetail item : orders.getOrderDetails()) {
                        Product product = productRepo.findById(item.getProductDetail().getProduct().getId()).orElse(null);
                        ProductDetail detail = product.getProductDetails().stream()
                                .filter(d -> d.getId() == item.getProductDetail().getId())
                                .findFirst()
                                .orElse(null);
                        if (detail != null) {
                            detail.setQuantity(detail.getQuantity() + item.getQuantity());
                            detail.setQuantitySold(detail.getQuantitySold() - item.getQuantity());
                            product.setTotalQuantitySold(product.getTotalQuantitySold() - item.getQuantity());
                            product.setTotalQuantity(product.getTotalQuantity() + item.getQuantity());
                            productRepo.save(product);
                        }
                    }
                }
            }
            ordersRepo.updateStatus(status, id);
        }

        @Override
        public OrdersDTO getById(Long id) {
            return convertToDto(ordersRepo.findById(id).orElse(null));
        }

        @Override
        public OrdersDTO create(OrdersDTO ordersDTO) {
            Cart cart = cartRepo.getByUser(ordersDTO.getUser().getId());
//            //convert from cartDetail to orderDetail
//
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (CartDetail cartDetail : cart.getCartDetails()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProductDetail(cartDetail.getProductDetail());
                orderDetail.setQuantity(cartDetail.getQuantity());
                orderDetails.add(orderDetail);
            }
//             save to order
            ordersDTO.setToltalMoney(cart.getTotalMoney());
            ordersDTO.setTotalProduct(cart.getTotalProduct());
            ordersDTO.setStatus(1);
            //
            for (CartDetail item : cart.getCartDetails()) {
                Product p = productRepo.findById(item.getProductDetail().getProduct().getId()).orElse(null);
                if (p != null) {
                    ProductDetail detail = p.getProductDetails().stream()
                            .filter(d -> d.getId() == item.getProductDetail().getId())
                            .findFirst()
                            .orElse(null);
                    if (detail != null) {
                        detail.setQuantitySold(detail.getQuantitySold() + item.getQuantity());
                        detail.setQuantity(detail.getQuantity() - item.getQuantity());
                        p.setTotalQuantity(p.getTotalQuantity() - item.getQuantity());
                        p.setTotalQuantitySold(p.getTotalQuantitySold() + item.getQuantity());
                        productRepo.save(p);
                    }
                }

            }
            cartDetailRepo.deleteByCart(cart.getId());

            cart.setTotalMoney(new BigDecimal(0));
            cart.setTotalProduct(0L);
            cartRepo.save(cart);

            Orders orders = convertToEntity(ordersDTO);

            orders.setOrderDetails(orderDetails);
            ordersRepo.save(orders);
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
