package com.project2.controller;

import com.project2.dto.ReponseDTO;
import com.project2.dto.ScoreDTO;
import com.project2.entity.Score;
import com.project2.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private IScoreService scoreService;

    @GetMapping("/allScore")
    public ReponseDTO<List<ScoreDTO>> getAll() {
        return ReponseDTO.<List<ScoreDTO>>builder()
                .data(scoreService.getAll().getData())
                .status(200)
                .msg("Get all score success")
                .build();
    }

    @PostMapping("")
    public ReponseDTO<ScoreDTO> create(@RequestBody ScoreDTO scoreDTO) {
        scoreService.create(scoreDTO);
        return ReponseDTO.<ScoreDTO>builder()
                .data(scoreDTO)
                .status(200)
                .msg("Create score success")
                .build();
    }

    @PutMapping("/{id}")
    public ReponseDTO<ScoreDTO> update(@RequestBody ScoreDTO scoreDTO,
                                       @PathVariable("id") Integer id) {
        scoreDTO.setId(id);
        scoreService.update(scoreDTO);
        return ReponseDTO.<ScoreDTO>builder()
                .data(scoreDTO)
                .status(200)
                .msg("Update score success")
                .build();
    }
    @DeleteMapping("/{id}")
    public ReponseDTO<Void> update(@PathVariable("id") Integer id) {
        scoreService.delete(id);
        return ReponseDTO.<Void>builder()
                .status(200)
                .msg("Delete score success")
                .build();
    }




}
