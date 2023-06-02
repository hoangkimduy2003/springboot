package com.education.reponsitory;

import com.education.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreReponsitory extends JpaRepository<Score,Integer> {
}
