package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.SizeDTO;
import com.ecommerce.ecommerce.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
@CrossOrigin
public class SizeController {
    @Autowired
    private ISizeService sizeService;

    @GetMapping("")
    public List<SizeDTO> getAll() {
        return sizeService.getAll();
    }

    @GetMapping("/{id}")
    public SizeDTO getById(@PathVariable("id") Long id) {
        return sizeService.getById(id);
    }

    @PostMapping("")
    public SizeDTO create(@RequestBody SizeDTO sizeDTO) {
        return sizeService.create(sizeDTO);
    }

    @PutMapping("/{id}")
    public SizeDTO update(@RequestBody SizeDTO sizeDTO,
                          @PathVariable("id") Long id) {
        sizeDTO.setId(id);
        return sizeService.update(sizeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        sizeService.delete(id);
    }

}
