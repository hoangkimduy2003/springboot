package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReponsitory extends JpaRepository<User,Long> {
}
