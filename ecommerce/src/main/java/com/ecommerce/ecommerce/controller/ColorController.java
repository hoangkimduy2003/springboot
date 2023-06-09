package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ColorDTO;
import com.ecommerce.ecommerce.dto.SizeDTO;
import com.ecommerce.ecommerce.service.IColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
@CrossOrigin
public class ColorController {

    @Autowired
    private IColorService colorService;

    @GetMapping("")
    public List<ColorDTO> getAll() {
        return colorService.getAll();
    }

    @GetMapping("/{id}")
    public ColorDTO getById(@PathVariable("id") Long id) {
        return colorService.getById(id);
    }

    @PostMapping("")
    public ColorDTO create(@RequestBody @Valid ColorDTO colorDTO) {
        return colorService.create(colorDTO);
    }

    @PutMapping("/{id}")
    public ColorDTO update(@RequestBody @Valid ColorDTO colorDTO,
                          @PathVariable("id") Long id) {
        colorDTO.setId(id);
        return colorService.update(colorDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        colorService.delete(id);
    }
}
