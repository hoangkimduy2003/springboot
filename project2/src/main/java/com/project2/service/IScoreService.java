package com.project2.service;

import com.project2.dto.AvgScoreByCourse;
import com.project2.dto.AvgScoreByStudent;
import com.project2.dto.PageDTO;
import com.project2.dto.ScoreDTO;
import com.project2.entity.Score;
import com.project2.reponsitory.ScoreReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IScoreService {

    ScoreDTO convertToDto(Score score);

    Score convertToEntity(ScoreDTO scoreDTO);

    PageDTO<List<ScoreDTO>> getAll();

    void create(ScoreDTO scoreDTO);

    void update(ScoreDTO scoreDTO);

    void delete(Integer id);

    ScoreDTO getById(Integer id);

    List<AvgScoreByCourse> getAvgScoreByCourse();

    List<AvgScoreByStudent> getAvgScoreByStudent();

    @Service
    class ScoreService implements IScoreService {

        @Autowired
        private ScoreReponsitory scoreRepo;

        @Override
        public ScoreDTO convertToDto(Score score) {
            return new ModelMapper().map(score, ScoreDTO.class);
        }

        @Override
        public Score convertToEntity(ScoreDTO scoreDTO) {
            return new ModelMapper().map(scoreDTO, Score.class);
        }

        @Override
        public PageDTO<List<ScoreDTO>> getAll() {
            Page<Score> pageEntity = scoreRepo.findAll(PageRequest.of(0, 150));
            List<ScoreDTO> list = pageEntity.get().map(u -> convertToDto(u))
                    .collect(Collectors.toList());
            return PageDTO.<List<ScoreDTO>>builder()
                    .data(list)
                    .totalPages(pageEntity.getTotalPages())
                    .totalElements(pageEntity.getTotalElements())
                    .build();
        }

        @Override
        public void create(ScoreDTO scoreDTO) {
            scoreRepo.save(convertToEntity(scoreDTO));
        }

        @Override
        public void update(ScoreDTO scoreDTO) {
            Score score = scoreRepo.findById(scoreDTO.getId()).orElse(null);
            if (score != null) {
                score = convertToEntity(scoreDTO);
            }
            scoreRepo.save(score);
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
        public List<AvgScoreByCourse> getAvgScoreByCourse() {
            return scoreRepo.avgScoreByCourse();
        }

        @Override
        public List<AvgScoreByStudent> getAvgScoreByStudent() {
            return scoreRepo.avgScoreByStudent();
        }
    }
}
