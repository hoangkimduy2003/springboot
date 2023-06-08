package com.example.demotieptheo.config;

import com.example.demotieptheo.dto.LoginDTO;
import com.example.demotieptheo.dto.ResponseDTO;
import com.example.demotieptheo.service.IUserService;
import com.example.demotieptheo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil JwtTokenUtil;

    @Autowired
    private IUserService userService;

    @PostMapping("")
    public ResponseDTO<String>login(@RequestBody LoginDTO loginDTO) throws Exception{
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        return ResponseDTO.<String>builder()
                .status(200)
                .data(JwtTokenUtil.createToken(loginDTO.getEmail()))
                .build();
    }

}
