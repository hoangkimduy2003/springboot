package com.example.demotieptheo.reponsetory;

import com.example.demotieptheo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReponsitory extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
