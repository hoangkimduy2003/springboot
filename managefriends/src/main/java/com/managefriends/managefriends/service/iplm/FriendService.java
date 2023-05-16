package com.managefriends.managefriends.service.iplm;

import com.managefriends.managefriends.dto.FriendDTO;
import com.managefriends.managefriends.entity.Friend;
import com.managefriends.managefriends.reponsitory.FriendReponsitory;
import com.managefriends.managefriends.service.IFriendService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FriendService implements IFriendService {

    @Autowired
    private FriendReponsitory friendReponsitory;

    @Override
    public FriendDTO convertToDto(Friend friend) {
        return new ModelMapper().map(friend, FriendDTO.class);
    }

    @Override
    public Friend convertToEntity(FriendDTO friendDTO) {
        return new ModelMapper().map(friendDTO, Friend.class);
    }

    @Override
    public List<FriendDTO> getFriends() {
        return friendReponsitory.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    @Override
    public void addFriend(FriendDTO friendDTO) {
        friendReponsitory.save(convertToEntity(friendDTO));
    }

    @Override
    public void updateFriend(FriendDTO friendDTO) {
        Friend friend = friendReponsitory.findById(friendDTO.getId()).orElse(null);
        if(friend!=null){
            friend = convertToEntity(friendDTO);
        }
        friendReponsitory.save(friend);
    }

    @Override
    public void deleteFriend(Integer id) {
        friendReponsitory.deleteById(id);
    }
}
