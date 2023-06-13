package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.CartDetailDTO;
import com.ecommerce.ecommerce.dto.ResponseDTO;
import com.ecommerce.ecommerce.service.ICartDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartDetail")
@CrossOrigin
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
    @Transactional
    public ResponseDTO<Void> delete(@PathVariable("id") Long id){
        cartDetailService.delete(id);
        return  ResponseDTO.<Void>builder()
                .status(200)
                .msg("Xoá thành công")
                .build();
    }

    @DeleteMapping("/cart/{id}")
    public ResponseDTO<Void> deleteByUser(@PathVariable("id") Long id){
        cartDetailService.deleteByCart(id);
        return  ResponseDTO.<Void>builder()
                .status(200)
                .msg("Xoá thành công")
                .build();
    }
}
