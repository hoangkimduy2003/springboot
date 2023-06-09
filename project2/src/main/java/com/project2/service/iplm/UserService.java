package com.project2.service.iplm;

import com.project2.dto.PageDTO;
import com.project2.dto.UserDTO;
import com.project2.entity.User;
import com.project2.reponsitory.UserReponsitory;
import com.project2.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserReponsitory userRepo;


    @Override
    public UserDTO convertToDto(User user) {
        return new ModelMapper().map(user,UserDTO.class);
    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
        return new ModelMapper().map(userDTO,User.class);
    }

    @Override
    public PageDTO<List<UserDTO>> convertPageDTO(Page<User> pageEntity) {
        PageDTO<List<UserDTO>> pageDTO = new PageDTO<>();
        List<UserDTO> list = pageEntity.get().map(u -> convertToDto(u)).collect(Collectors.toList());
        pageDTO.setData(list);
        pageDTO.setTotalPages(pageEntity.getTotalPages());
        pageDTO.setTotalElements(pageEntity.getTotalElements());
        return pageDTO;
    }

    @Transactional
    @Override
    public void create(UserDTO userDTO) {
        User user = convertToEntity(userDTO);

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
    }

    @Transactional
    @Override
    public void update(UserDTO userDTO) {
        User user = userRepo.findById(userDTO.getId()).orElse(null);
        if(user != null){
            user = convertToEntity(userDTO);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userRepo.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public PageDTO<List<UserDTO>> getAll() {
        Page<User> page = userRepo.findAll(PageRequest.of(0,100));
        return convertPageDTO(page);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepo.findByUsername(username);
        if(userEntity ==null){
            throw new UsernameNotFoundException("Not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(String role: userEntity.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(username,userEntity.getPassword(), authorities);
    }
}
