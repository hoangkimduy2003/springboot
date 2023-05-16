package com.managefriends.managefriends.reponsitory;

import com.managefriends.managefriends.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendReponsitory extends JpaRepository<Friend,Integer> {
}
