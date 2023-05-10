package com.project1.reponsitory;

import com.project1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReponsitory extends JpaRepository<User,Integer> {
}
