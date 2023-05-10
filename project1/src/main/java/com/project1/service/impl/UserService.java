package com.project1.service.impl;

import com.project1.dto.PageDTO;
import com.project1.dto.UserDTO;
import com.project1.entity.User;
import com.project1.reponsitory.UserReponsitory;
import com.project1.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserReponsitory userReponsitory;

    public UserDTO convert(User user) {
        return new ModelMapper().map(user, UserDTO.class);
    }

    @Override
    public PageDTO<List<UserDTO>> getAll(int page) {
        PageDTO<List<UserDTO>> pageDTO = new PageDTO<>();
        Page<User> pageEntity = userReponsitory.findAll(PageRequest.of(page, 15));
        pageDTO.setTotalPages(pageEntity.getTotalPages());
        pageDTO.setTotalElements(pageEntity.getTotalElements());

        List<UserDTO> listDTO = pageEntity.get().map(u -> convert(u))
                .collect(Collectors.toList());

        pageDTO.setData(listDTO);

        return pageDTO;
    }

    @Override
    public void delete(Integer id) {
        userReponsitory.deleteById(id);
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = userReponsitory.findById(userDTO.getId()).orElse(null);
        if(user != null){
            user = new ModelMapper().map(userDTO,User.class);
        }
        userReponsitory.save(user);
    }

    @Override
    public void create(UserDTO userDTO) {
        userReponsitory.save(new ModelMapper().map(userDTO,User.class));
    }

    @Override
    public UserDTO getById(Integer id) {
        return new ModelMapper().map(userReponsitory.findById(id).orElse(null),UserDTO.class);
    }


}
