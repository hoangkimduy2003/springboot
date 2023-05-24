package com.project2.reponsitory;

import com.project2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentReponsitory extends JpaRepository<Student,Integer> {
}
