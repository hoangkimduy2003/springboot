package com.managefriends.managefriends.controller;

import com.managefriends.managefriends.dto.FriendDTO;
import com.managefriends.managefriends.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private IFriendService friendService;

    @GetMapping("")
    public List<FriendDTO> getFriends(){
        return friendService.getFriends();
    }

    @PostMapping("/addFriend")
    public void addFriend(@RequestBody FriendDTO friendDTO){
        friendService.addFriend(friendDTO);
    }

    @PutMapping("/{id}/edit")
    public void updateFriend(@PathVariable("id") Integer id,
                             @RequestBody FriendDTO friendDTO){
        friendDTO.setId(id);
        friendService.updateFriend(friendDTO);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteFriend(@PathVariable("id") Integer id){
        friendService.deleteFriend(id);
    }
}
