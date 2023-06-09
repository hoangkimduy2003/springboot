package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ColorDTO;
import com.ecommerce.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.ecommerce.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetail")
@CrossOrigin
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("")
    public List<OrderDetailDTO> getAll() {
        return orderDetailService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDetailDTO getById(@PathVariable("id") Long id) {
        return orderDetailService.getById(id);
    }

    @PostMapping("")
    public OrderDetailDTO create(@RequestBody OrderDetailDTO orderDetailDTO) {
        return orderDetailService.create(orderDetailDTO);
    }

    @PutMapping("/{id}")
    public OrderDetailDTO update(@RequestBody OrderDetailDTO orderDetailDTO,
                                 @PathVariable("id") Long id) {
        orderDetailDTO.setId(id);
        return orderDetailService.update(orderDetailDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderDetailService.delete(id);
    }
}
