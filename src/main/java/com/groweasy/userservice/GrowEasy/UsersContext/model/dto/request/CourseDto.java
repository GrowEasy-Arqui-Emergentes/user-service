package com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request;

import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private String price;
    private String rating;
    private String duration;
    private String date;
}
