package com.education.reponsitory;

import com.education.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentReponsitory extends JpaRepository<Department,Integer> {
}
