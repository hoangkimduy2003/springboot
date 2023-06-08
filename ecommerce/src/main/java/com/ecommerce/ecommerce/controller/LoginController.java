package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.LoginDTO;
import com.ecommerce.ecommerce.dto.ResponseDTO;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.reponsitory.UserReponsitory;
import com.ecommerce.ecommerce.security.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    private UserReponsitory userRepo;

    @PostMapping("")
    public ResponseDTO<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        User user = userRepo.findByEmail(loginDTO.getEmail());
        return ResponseDTO.<String>builder()
                .status(200)
                .data(jwtTokenService.createToken(user))
                .build();
    }

}
