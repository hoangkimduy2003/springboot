package com.project2.controller;

import com.project2.dto.ReponseDTO;
import com.project2.dto.UserDTO;
import com.project2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ReponseDTO<UserDTO> create(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return ReponseDTO.<UserDTO>builder().status(200)
                .msg("Create success")
                .data(userDTO)
                .build();
    }
}
