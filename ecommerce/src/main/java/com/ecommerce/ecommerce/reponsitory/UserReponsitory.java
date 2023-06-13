package com.ecommerce.ecommerce.reponsitory;

import com.ecommerce.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserReponsitory extends JpaRepository<User,Long> {
    @Query("SELECT u from User u where u.email = :name")
    User getByUser(@Param("name") String email);

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String username);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password , @Param("id") Long id);
}
