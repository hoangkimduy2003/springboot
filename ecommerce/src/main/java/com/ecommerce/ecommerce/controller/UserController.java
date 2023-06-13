package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ChangePasswordDTO;
import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
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

    @GetMapping("/email/{email}")
    public UserDTO getByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }


    @PostMapping("")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/updatePassword")
    public void updatePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        userService.updatePassword(changePasswordDTO);
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
