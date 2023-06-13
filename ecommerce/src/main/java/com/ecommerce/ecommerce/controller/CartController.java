package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("")
    public List<CartDTO> getAll(){
        return cartService.getAll();
    }
    @GetMapping("/user/{id}")
    public CartDTO getByUser(@PathVariable("id")Long id){
        return cartService.getByUser(id);
    }

    @GetMapping("/{id}")
    public CartDTO getById(@PathVariable("id") Long id){
        return cartService.getById(id);
    }

    @PostMapping("")
    public CartDTO create(@RequestBody CartDTO cartDTO){
        return cartService.create(cartDTO);
    }

    @PutMapping("/{id}")
    public CartDTO update(@RequestBody CartDTO cartDTO,
                          @PathVariable("id") Long id){
        cartDTO.setId(id);
        return cartService.update(cartDTO);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable("id") Long id){
        cartService.delete(id);
    }


}
