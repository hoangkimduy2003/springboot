package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.CartDetailDTO;
import com.ecommerce.ecommerce.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartDetail")
public class CartDetailController {

    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping("")
    public List<CartDetailDTO> getAll(){
        return cartDetailService.getAll();
    }

    @GetMapping("/{id}")
    public CartDetailDTO getById(@PathVariable("id") Long id){
        return cartDetailService.getById(id);
    }

    @PostMapping("")
    public CartDetailDTO create(@RequestBody CartDetailDTO cartDetailDTO){
        return cartDetailService.create(cartDetailDTO);
    }

    @PutMapping("/{id}")
    public CartDetailDTO update(@RequestBody CartDetailDTO cartDetailDTO,
                                @PathVariable("id") Long id){
        cartDetailDTO.setId(id);
        return cartDetailService.update(cartDetailDTO);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable("id") Long id){
        cartDetailService.delete(id);
    }
}
