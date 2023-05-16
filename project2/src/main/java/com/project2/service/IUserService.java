package com.project2.service;

import com.project2.dto.PageDTO;
import com.project2.dto.UserDTO;
import com.project2.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    UserDTO convertToDto(User user);
    User convertToEntity(UserDTO userDTO);
    PageDTO<List<UserDTO>> convertPageDTO(Page<User> page);
    void create(UserDTO userDTO);
    void update(UserDTO userDTO);
    void delete(Integer id);
    PageDTO<UserDTO> getAll();
}
