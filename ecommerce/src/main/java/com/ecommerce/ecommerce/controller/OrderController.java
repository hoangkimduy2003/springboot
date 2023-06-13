package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.OrderUserStatusDTO;
import com.ecommerce.ecommerce.dto.OrdersDTO;
import com.ecommerce.ecommerce.service.IOrdersService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrdersService ordersService;

    @GetMapping("")
    public List<OrdersDTO> getAll() {
        return ordersService.getAll();
    }

    @GetMapping("/{id}")
    public OrdersDTO getById(@PathVariable Long id){
        return ordersService.getById(id);
    }

    @PostMapping("/orderUser")
    public List<OrdersDTO> getByUserAndStatus(@RequestBody OrderUserStatusDTO orderUserStatusDTO){
        return ordersService.getByUserAndStatus(orderUserStatusDTO.getId(),orderUserStatusDTO.getStatus());
    }

    @GetMapping("/user/{id}")
    public List<OrdersDTO> getByUser(@PathVariable Long id){
        return ordersService.getByUser(id);
    }

    @PostMapping("")
    public OrdersDTO create(@RequestBody OrdersDTO ordersDTO) {
        ordersService.create(ordersDTO);
        return ordersDTO;
    }

    @PutMapping("/{id}")
    public OrdersDTO update(@RequestBody OrdersDTO ordersDTO,
                            @PathVariable("id") Long id) {
        ordersDTO.setId(id);
        ordersService.create(ordersDTO);
        return ordersDTO;
    }

    @PutMapping("/updateStatus")
    public void updateStatus(@RequestBody OrderUserStatusDTO orderUserStatusDTO){
         ordersService.updateStatus(orderUserStatusDTO.getStatus(),orderUserStatusDTO.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        ordersService.delete(id);
    }
}
