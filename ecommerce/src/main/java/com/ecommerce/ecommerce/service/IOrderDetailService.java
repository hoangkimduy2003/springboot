package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.ecommerce.entity.OrderDetail;
import com.ecommerce.ecommerce.reponsitory.OrderDetailReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IOrderDetailService {
    OrderDetailDTO convertToDto(OrderDetail orderDetail);

    OrderDetail convertToEntity(OrderDetailDTO orderDetailDTO);

    List<OrderDetailDTO> getAll();

    OrderDetailDTO getById(Long id);

    OrderDetailDTO create(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO update(OrderDetailDTO orderDetailDTO);

    void delete(Long id);

    @Service
    class OrderDetailService implements IOrderDetailService {

        @Autowired
        private OrderDetailReponsitory orderDetailRepo;

        @Override
        public OrderDetailDTO convertToDto(OrderDetail orderDetail) {
            return new ModelMapper().map(orderDetail, OrderDetailDTO.class);
        }

        @Override
        public OrderDetail convertToEntity(OrderDetailDTO sizeDTO) {
            return new ModelMapper().map(sizeDTO, OrderDetail.class);
        }

        @Override
        public List<OrderDetailDTO> getAll() {
            return orderDetailRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public OrderDetailDTO getById(Long id) {
            return convertToDto(orderDetailRepo.findById(id).orElse(null));
        }

        @Override
        public OrderDetailDTO create(OrderDetailDTO orderDetailDTO) {
            orderDetailRepo.save(convertToEntity(orderDetailDTO));
            return orderDetailDTO;
        }

        @Override
        public OrderDetailDTO update(OrderDetailDTO orderDetailDTO) {
            OrderDetail orderDetail = orderDetailRepo.findById(orderDetailDTO.getId()).orElse(null);
            if (orderDetail != null) {
                orderDetail = convertToEntity(orderDetailDTO);
            }
            orderDetailRepo.save(orderDetail);
            return orderDetailDTO;
        }

        @Override
        public void delete(Long id) {
            orderDetailRepo.deleteById(id);
        }
    }

}
