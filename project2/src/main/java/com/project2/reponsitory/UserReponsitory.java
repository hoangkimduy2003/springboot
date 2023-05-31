package com.project2.reponsitory;

import com.project2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserReponsitory extends JpaRepository<User,Integer> {

    @Query("select u from User u where DAY(u.birthdate) = :day and MONTH(u.birthdate) = :month")
    List<User> searchByBirthDate(@Param("day") int day,@Param("month") int month);
}
