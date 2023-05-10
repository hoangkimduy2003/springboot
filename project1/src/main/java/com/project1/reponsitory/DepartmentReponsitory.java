package com.project1.reponsitory;

import com.project1.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentReponsitory extends JpaRepository<Department,Integer> {
    @Query("select d from  Department  d where d.name like :x")
    Page<Department> searchByKeyWordName(@Param("x") String x, Pageable pageable);
}
