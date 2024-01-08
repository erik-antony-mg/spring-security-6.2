package com.example.services.impl;

import com.example.entities.ERole;
import com.example.entities.RoleEntity;
import com.example.entities.UserEntity;
import com.example.entities.dto.UserEntityDto;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserEntityDto userEntityDto) {
        UserEntity user= modelMapper.map(userEntityDto,UserEntity.class);
        Set<RoleEntity> roles=userEntityDto.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build()).collect(Collectors.toSet());

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userEntityDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(()-> new  RuntimeException("id no encontrado"));
        userRepository.deleteById(user.getIdUserEntity());
    }
}
