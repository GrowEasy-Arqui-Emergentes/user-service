package com.groweasy.userservice.GrowEasy.UsersContext.service;

import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.UserRequestDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.response.UserResponseDto;

public interface UsersService {

    //Get
    UserResponseDto getUserForLogin(String email, String password);

    UserResponseDto getUserById(Long id);

    //Create
    UserResponseDto createUser(UserRequestDto user);

    //Update
    UserResponseDto updateUser(Long id, UserRequestDto user);



}
