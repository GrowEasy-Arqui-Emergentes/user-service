package com.groweasy.userservice.GrowEasy.UsersContext.service;

import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.CourseDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.UserRequestDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.response.UserResponseDto;

import java.util.List;

public interface UsersService {

    //Get
    UserResponseDto getUserForLogin(String email, String password);

    UserResponseDto getUserById(Long id);

    //Create
    UserResponseDto createUser(UserRequestDto user);

    //Update
    UserResponseDto updateUser(Long id, UserRequestDto user);

    UserResponseDto addCourseToUser(Long userId, Long courseId);

    List<CourseDto> getUserCourses(Long userId);



}
