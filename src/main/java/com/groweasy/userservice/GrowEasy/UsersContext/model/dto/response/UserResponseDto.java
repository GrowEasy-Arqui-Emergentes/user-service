package com.groweasy.userservice.GrowEasy.UsersContext.model.dto.response;

import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseDto> courses;
}
