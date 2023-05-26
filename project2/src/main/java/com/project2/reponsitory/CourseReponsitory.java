package com.project2.reponsitory;

import com.project2.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReponsitory extends JpaRepository<Course,Integer> {
}
