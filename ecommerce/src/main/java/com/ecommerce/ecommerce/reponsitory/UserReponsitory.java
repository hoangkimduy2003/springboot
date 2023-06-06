package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserReponsitory extends JpaRepository<User,Long> {
    @Query("SELECT u from User u where u.email = :name")
    User getByUser(@Param("name") String email);
}
