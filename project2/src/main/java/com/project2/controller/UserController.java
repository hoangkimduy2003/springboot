package com.project2.controller;

import com.project2.dto.ReponseDTO;
import com.project2.dto.UserDTO;
import com.project2.service.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ReponseDTO<List<UserDTO>> getAll() {
        return ReponseDTO.<List<UserDTO>>builder()
                .status(200)
                .msg("Get all success")
                .data(userService.getAll().getData())
                .build();
    }

    @GetMapping("/download")
    public void download(@RequestParam("filename") String filename,
                         HttpServletResponse resp) throws IOException {

        File file = new File("D:/file/" + filename);
        Files.copy(file.toPath(), resp.getOutputStream());
    }

    @PostMapping("")
    public ReponseDTO<UserDTO> create(@RequestBody @Valid UserDTO userDTO) throws IOException {

        if (!userDTO.getFile().isEmpty()) {
            String fileName = userDTO.getFile().getOriginalFilename();
            File saveFile = new File("D:/file" + fileName);
            userDTO.getFile().transferTo(saveFile);
            userDTO.setAvatarURL(fileName);
        }
        userService.create(userDTO);
        return ReponseDTO.<UserDTO>builder()
                .status(200)
                .data(userDTO)
                .msg("Create success")
                .build();
    }

    @DeleteMapping("")
    public ReponseDTO<Void> delete(@RequestParam("id") Integer id) {
        return ReponseDTO.<Void>builder()
                .status(200)
                .build();
    }

    @PutMapping("")
    public ReponseDTO<UserDTO> update(
            @RequestBody @Valid UserDTO userDTO
    ) throws IOException {
        if(!userDTO.getFile().isEmpty()){
            String fileName =userDTO.getFile().getOriginalFilename();
            File saveFile = new File("D:/file"+fileName);
            userDTO.getFile().transferTo(saveFile);
            userDTO.setAvatarURL(fileName);
        }
        userService.update(userDTO);
        return ReponseDTO.<UserDTO>builder()
                .status(200)
                .data(userDTO)
                .build();
    }

}
