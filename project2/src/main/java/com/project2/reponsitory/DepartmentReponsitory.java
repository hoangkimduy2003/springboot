package com.project2.reponsitory;

import com.project2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentReponsitory extends JpaRepository<Department,Integer> {
}
