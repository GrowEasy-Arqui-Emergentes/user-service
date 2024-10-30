package com.groweasy.userservice.GrowEasy.UsersContext.repository;

import com.groweasy.userservice.GrowEasy.UsersContext.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Course, Long> {

    Boolean existsByNameAndPrice(String name, String price);
}