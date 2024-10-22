package com.groweasy.userservice.GrowEasy.UsersContext.service.impl;

import com.groweasy.userservice.GrowEasy.UsersContext.service.UsersService;
import com.groweasy.userservice.GrowEasy.Shared.exception.ResourceNotFoundException;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.UserRequestDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.response.UserResponseDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.entity.User;
import com.groweasy.userservice.GrowEasy.UsersContext.repository.UsersRepository;
import com.groweasy.userservice.GrowEasy.UsersContext.validations.UserValidation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UsersServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (usersRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        var newUser = modelMapper.map(userRequestDto, User.class);

        var createdUser = usersRepository.save(newUser);
        return modelMapper.map(createdUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserForLogin(String email, String password) {
        var user = usersRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new ResourceNotFoundException("No existe un usuario con ese email y contraseña");
        }
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con el id: " + id));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con el id: " + id));

        if(userRequestDto.getFirstName() != null) {
            UserValidation.validateUserFirstName(userRequestDto.getFirstName());
            user.setFirstName(userRequestDto.getFirstName());
        }
        if(userRequestDto.getLastName() != null) {
            UserValidation.validateUserLastName(userRequestDto.getLastName());
            user.setLastName(userRequestDto.getLastName());
        }
        if(userRequestDto.getEmail() != null) {
            UserValidation.validateUserEmail(userRequestDto.getEmail());
            user.setEmail(userRequestDto.getEmail());
        }
        if(userRequestDto.getPassword() != null) {
            UserValidation.validateUserPassword(userRequestDto.getPassword());
            user.setPassword(userRequestDto.getPassword());
        }

        User updatedUser = usersRepository.save(user);
        return modelMapper.map(updatedUser, UserResponseDto.class);
    }
}
