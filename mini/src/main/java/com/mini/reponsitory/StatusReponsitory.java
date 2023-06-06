package com.mini.reponsitory;

import com.mini.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusReponsitory extends JpaRepository<Status,Long> {
}
