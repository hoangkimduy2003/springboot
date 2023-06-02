package com.education.service;

import com.education.dto.ScoreDTO;
import com.education.entity.Score;
import com.education.reponsitory.ScoreReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IScoreService {
    ScoreDTO convertToDto(Score score);

    Score convertToEntity(ScoreDTO scoreDTO);

    List<ScoreDTO> getAll();

    void delete(Integer id);

    ScoreDTO getById(Integer id);

    void create(ScoreDTO scoreDTO);

    void update(ScoreDTO scoreDTO);

    @Service
    class ScoreService implements IScoreService{
        @Autowired
        private ScoreReponsitory scoreRepo;

        @Override
        public ScoreDTO convertToDto(Score score) {
            return new ModelMapper().map(score,ScoreDTO.class);
        }

        @Override
        public Score convertToEntity(ScoreDTO scoreDTO) {
            return new ModelMapper().map(scoreDTO,Score.class);
        }

        @Override
        public List<ScoreDTO> getAll() {
            return scoreRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public void delete(Integer id) {
            scoreRepo.deleteById(id);
        }

        @Override
        public ScoreDTO getById(Integer id) {
            return convertToDto(scoreRepo.findById(id).orElse(null));
        }

        @Override
        public void create(ScoreDTO scoreDTO) {
            for (Score x : scoreRepo.findAll()){
                if(scoreDTO.getStudent().getId().equals(x.getStudent().getId())
                && scoreDTO.getCourse().getId().equals((x.getCourse().getId()))){
                    scoreDTO.setId(x.getId());
                }
            }
            scoreRepo.save(convertToEntity(scoreDTO));
        }

        @Override
        public void update(ScoreDTO scoreDTO) {
            Score scoreEntity = scoreRepo.findById(scoreDTO.getId()).orElse(null);
            if(scoreEntity != null){
                scoreEntity = convertToEntity(scoreDTO);
            }
            scoreRepo.save(scoreEntity);
        }
    }
}
