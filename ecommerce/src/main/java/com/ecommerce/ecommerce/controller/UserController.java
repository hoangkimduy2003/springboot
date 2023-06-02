package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO,
                             @PathVariable("id") Long id) {
        userDTO.setId(id);
        return userService.update(userDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}