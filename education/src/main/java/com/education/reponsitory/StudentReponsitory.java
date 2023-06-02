package com.education.reponsitory;

import com.education.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentReponsitory extends JpaRepository<Student, Integer> {
}
