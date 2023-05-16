package com.managefriends.managefriends.service;

import com.managefriends.managefriends.dto.FriendDTO;
import com.managefriends.managefriends.entity.Friend;

import java.util.List;

public interface IFriendService {
    FriendDTO convertToDto(Friend friend);
    Friend convertToEntity(FriendDTO friendDTO);
    List<FriendDTO> getFriends();
    void addFriend(FriendDTO friendDTO);
    void updateFriend(FriendDTO friendDTO);
    void deleteFriend(Integer id);

}
