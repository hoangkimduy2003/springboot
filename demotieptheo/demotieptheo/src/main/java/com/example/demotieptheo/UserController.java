package com.example.demotieptheo;

import com.example.demotieptheo.dto.UserDTO;
import com.example.demotieptheo.service.IUserService;
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

    @GetMapping("/{email}")
    public UserDTO getByEmail(@PathVariable("email") String email) {
        return userService.getByEmail(email);
    }

    @PostMapping("")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        userService.craete(userDTO);
        return userDTO;
    }
}
