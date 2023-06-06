package com.mini.controller;


import com.mini.dto.ResponseDTO;
import com.mini.dto.StatusDTO;
import com.mini.entity.Status;
import com.mini.service.IStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private IStatusService statusService;

    @GetMapping("")
    public ResponseDTO<List<StatusDTO>> getAll() {
        return ResponseDTO.<List<StatusDTO>>builder()
                .data(statusService.getAll())
                .msg("Get all success status")
                .status(200)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<StatusDTO> getById(@PathVariable("id") Long id) {
        return ResponseDTO.<StatusDTO>builder()
                .status(200)
                .msg("Get success")
                .data(statusService.getById(id))
                .build();
    }

    @PostMapping("")
    public ResponseDTO<StatusDTO> create(@RequestBody @Valid StatusDTO statusDTO) {
        statusService.create(statusDTO);
        return ResponseDTO.<StatusDTO>builder()
                .status(200)
                .data(statusDTO)
                .msg("Craete status success")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<StatusDTO> update(@RequestBody @Valid StatusDTO statusDTO,
                                         @PathVariable("id") Long id) {
        statusDTO.setId(id);
        statusService.update(statusDTO);
        return ResponseDTO.<StatusDTO>builder()
                .data(statusDTO)
                .msg("Update success")
                .status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") Long id) {
        statusService.delete(id);
        return ResponseDTO.<Void>builder()
                .msg("Update success")
                .status(200)
                .build();
    }
}
