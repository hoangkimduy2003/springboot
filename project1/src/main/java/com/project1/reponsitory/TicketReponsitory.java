package com.project1.reponsitory;

import com.project1.entity.Department;
import com.project1.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketReponsitory extends JpaRepository<Ticket, Integer> {
    @Query("select d from  Ticket  d where d.phoneNumber like :x")
    Page<Ticket> searchByKeyWord(@Param("x") String x, Pageable pageable);
}
