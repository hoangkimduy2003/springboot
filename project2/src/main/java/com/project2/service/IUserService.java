package com.project2.service;

import com.project2.dto.UserDTO;

public interface IUserService {
    void create(UserDTO userDTO);
    void update(UserDTO userDTO);
    void delete(Integer id);

}
