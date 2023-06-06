package com.mini.controller;

import com.mini.dto.BrandDTO;
import com.mini.dto.ResponseDTO;
import com.mini.service.IBrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping("")
    public ResponseDTO<List<BrandDTO>> getAll() {
        return ResponseDTO.<List<BrandDTO>>builder()
                .data(brandService.getAll())
                .msg("Get all success status")
                .status(200)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<BrandDTO> getById(@PathVariable("id") Long id) {
        return ResponseDTO.<BrandDTO>builder()
                .status(200)
                .msg("Get success")
                .data(brandService.getById(id))
                .build();
    }

    @PostMapping("")
    public ResponseDTO<BrandDTO> create(@RequestBody @Valid BrandDTO baBrandDTO) {
        brandService.create(baBrandDTO);
        return ResponseDTO.<BrandDTO>builder()
                .status(200)
                .data(baBrandDTO)
                .msg("Craete status success")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<BrandDTO> update(@RequestBody @Valid BrandDTO baBrandDTO,
                                         @PathVariable("id") Long id) {
        baBrandDTO.setId(id);
        brandService.update(baBrandDTO);
        return ResponseDTO.<BrandDTO>builder()
                .data(baBrandDTO)
                .msg("Update success")
                .status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") Long id) {
        brandService.delete(id);
        return ResponseDTO.<Void>builder()
                .msg("Update success")
                .status(200)
                .build();
    }
}
