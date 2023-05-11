package com.project1.reponsitory;

import com.project1.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TicketReponsitory extends JpaRepository<Ticket, Integer> {
    @Query("select d from  Ticket  d where d.phoneNumber like :x")
    Page<Ticket> searchByKeyWord(@Param("x") String x, Pageable pageable);

    @Query("select t from Ticket t join t.department d where t.createdAt >= :start and t.createdAt <= :end or " +
            "d.id = :departmentId or t.phoneNumber like :phoneNumber")
    Page<Ticket> searchByCreatedAt(@Param("start") Date start,
                                   @Param("end") Date end,
                                   @Param("departmentId") Integer id,
                                   @Param("phoneNumber") String phoneNumber,
                                   Pageable pageable);

}
