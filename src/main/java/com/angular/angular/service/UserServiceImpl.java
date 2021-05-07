package com.angular.angular.service;

import com.angular.angular.entity.RoleEntity;
import com.angular.angular.entity.UserEntity;
import com.angular.angular.repository.RoleRepository;
import com.angular.angular.repository.UserRepository;
import com.angular.angular.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto createUser(UserDto userDto) {
        // Check if username is already taken
        UserEntity userEntity = userRepository.findByUsername(userDto.getUsername());

        if (userEntity != null) throw new UsernameNotFoundException("User is already exists");

        UserEntity userEntity1 = modelMapper.map(userDto, UserEntity.class);
        userEntity1.setPassword(passwordEncoder.encode(userDto.getPassword()));

        RoleEntity roleEntity = roleRepository.findByRoleName("ROLE_ADMIN");
        userEntity1.setRole(roleEntity);

        UserEntity savedEntity = userRepository.save(userEntity1);

        UserDto userDto1 = modelMapper.map(savedEntity, UserDto.class);
        userDto1.setUuid(savedEntity.getId());

        return userDto1;
    }
}
