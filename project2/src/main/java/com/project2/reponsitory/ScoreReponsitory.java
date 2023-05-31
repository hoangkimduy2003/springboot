package com.project2.reponsitory;

import com.project2.dto.AvgScoreByCourse;
import com.project2.dto.AvgScoreByStudent;
import com.project2.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreReponsitory extends JpaRepository<Score,Integer> {
    @Query("select s from Score s where s.student.id = :sid")
    Page<Score> searchByStudent(@Param("sid") Integer id, Pageable pageable);

    @Query("select s from Score s where s.course.id = :cid")
    Page<Score> searchByCourse(@Param("cid") Integer id,Pageable pageable);

    @Query("select new com.project2.dto.AvgScoreByCourse(c.id,c.name,AVG(s.score))" +
            " from Score s join s.course c group by c.id,c.name")
    List<AvgScoreByCourse> avgScoreByCourse();

    @Query("select new com.project2.dto.AvgScoreByStudent(st.id,st.studentCode,st.user.name,AVG(s.score))" +
            " from Score s join s.student st group by st.id, st.studentCode ,st.user.name")
    List<AvgScoreByStudent> avgScoreByStudent();
}
