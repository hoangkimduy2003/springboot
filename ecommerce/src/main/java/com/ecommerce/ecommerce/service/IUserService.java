package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.reponsitory.UserReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface IUserService {
    UserDTO convertToDto(User user);

    User convertToEntity(UserDTO sizeDTO);

    List<UserDTO> getAll();

    UserDTO getById(Long id);

    UserDTO create(UserDTO sizeDTO);

    UserDTO update(UserDTO sizeDTO);

    void delete(Long id);

    UserDTO findByEmail(String email);

    @Service
    class UserService implements IUserService, UserDetailsService {

        @Autowired
        private UserReponsitory userRepo;

        @Override
        public UserDTO convertToDto(User user) {
            return new ModelMapper().map(user, UserDTO.class);
        }

        @Override
        public User convertToEntity(UserDTO sizeDTO) {
            return new ModelMapper().map(sizeDTO, User.class);
        }

        @Override
        public List<UserDTO> getAll() {
            return userRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public UserDTO getById(Long id) {
            return convertToDto(userRepo.findById(id).orElse(null));
        }

        @Override
        public UserDTO create(UserDTO userDTO) {
            User user = convertToEntity(userDTO);
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            userRepo.save(user);
            return userDTO;
        }

        @Override
        public UserDTO update(UserDTO userDTO) {
            User user = userRepo.findById(userDTO.getId()).orElse(null);
            if (user != null) {
                user = convertToEntity(userDTO);
            }
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            userRepo.save(user);
            return userDTO;
        }

        @Override
        public void delete(Long id) {
            userRepo.deleteById(id);
        }

        @Override
        public UserDTO findByEmail(String email) {
            return convertToDto(userRepo.findByEmail(email));
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User userEntity = userRepo.findByEmail(username);
            if (userEntity == null) {
                throw new UsernameNotFoundException("Not found");
            }
//            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(a)
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole());
            return new org.springframework.security.core.
                    userdetails.User(username,
                            userEntity.getPassword(), Collections.singleton(authority));
        }
    }
}
