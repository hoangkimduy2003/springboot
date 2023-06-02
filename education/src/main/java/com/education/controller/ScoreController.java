package com.education.controller;

import com.education.dto.ScoreDTO;
import com.education.entity.Score;
import com.education.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
@CrossOrigin
public class ScoreController {

    @Autowired
    private IScoreService scoreService;

    @GetMapping("")
    public List<ScoreDTO> getAll() {
        return scoreService.getAll();
    }

    @GetMapping("/{id}")
    public ScoreDTO getById(@PathVariable("id") Integer id) {
        return scoreService.getById(id);
    }

    @PostMapping("")
    public ScoreDTO create(@RequestBody ScoreDTO scoreDTO) {
        scoreService.create(scoreDTO);
        return scoreDTO;
    }

    @PutMapping("/{id}")
    public ScoreDTO update(@RequestBody ScoreDTO scoreDTO,
                           @PathVariable("id") Integer id) {
        scoreDTO.setId(id);
        scoreService.update(scoreDTO);
        return scoreDTO;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        scoreService.delete(id);
        return "Delete score success";
    }


}
