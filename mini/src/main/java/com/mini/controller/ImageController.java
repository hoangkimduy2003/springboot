package com.mini.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImageController {
    @GetMapping("/{fileName}")
    public void show(@PathVariable("fileName") String fileName, HttpServletResponse res) throws Exception{
        File file = new File("D:/project_vuejs/project/projectVueJs/mini/src/assets/images/" + fileName);
        Files.copy(file.toPath(),res.getOutputStream());
    }

}
