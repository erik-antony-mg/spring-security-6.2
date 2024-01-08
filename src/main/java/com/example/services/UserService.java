package com.example.services;

import com.example.entities.UserEntity;
import com.example.entities.dto.UserEntityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserEntity>getAllUsers();
    UserEntity createUser(UserEntityDto userEntityDto);
    void deleteUser(Long userId);
}
