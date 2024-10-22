package com.groweasy.userservice.GrowEasy.UsersContext.controller;

import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.UserRequestDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.response.UserResponseDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.entity.User;
import com.groweasy.userservice.GrowEasy.UsersContext.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponseDto> getUserForLogin(@RequestParam(name="email") String email, @RequestParam(name="password")String password){
        var res = usersService.getUserForLogin(email, password);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        var res = usersService.getUserById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        var res = usersService.createUser(userRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable(name="id") Long id, @RequestBody UserRequestDto userRequestDto){
        var res = usersService.updateUser(id, userRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
