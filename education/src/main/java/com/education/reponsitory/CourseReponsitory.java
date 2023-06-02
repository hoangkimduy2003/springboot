package com.education.reponsitory;

import com.education.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReponsitory extends JpaRepository<Course,Integer> {
}
