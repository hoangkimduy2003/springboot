package com.project2.reponsitory;

import com.project2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReponsitory extends JpaRepository<User,Integer> {
}
