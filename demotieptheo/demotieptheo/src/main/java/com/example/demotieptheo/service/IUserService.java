package com.example.demotieptheo.service;

import com.example.demotieptheo.dto.UserDTO;
import com.example.demotieptheo.entity.User;
import com.example.demotieptheo.reponsetory.UserReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface IUserService {
    UserDTO convertToDto(User user);

    User convertToEntity(UserDTO userDTO);

    List<UserDTO> getAll();

    void craete(UserDTO userDTO);

    void update(UserDTO userDTO);

    void delete(Long id);

    UserDTO getByEmail(String email);

    @Service
    class UserService implements IUserService, UserDetailsService {

        @Autowired
        private UserReponsitory userRepo;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepo.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User name not found");
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
        }

        @Override
        public UserDTO convertToDto(User user) {
            return new ModelMapper().map(user, UserDTO.class);
        }

        @Override
        public User convertToEntity(UserDTO userDTO) {
            return new ModelMapper().map(userDTO, User.class);
        }

        @Override
        public List<UserDTO> getAll() {
            return userRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public void craete(UserDTO userDTO) {
            userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            userRepo.save(convertToEntity(userDTO));
        }

        @Override
        public void update(UserDTO userDTO) {
            User user = userRepo.findById(userDTO.getId()).orElseThrow(IllegalArgumentException::new);
            if (user != null) {
                user = convertToEntity(userDTO);
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepo.save(user);
        }

        @Override
        public void delete(Long id) {
            userRepo.deleteById(id);
        }

        @Override
        public UserDTO getByEmail(String email) {
            return convertToDto(userRepo.findByEmail(email));
        }


    }
}
