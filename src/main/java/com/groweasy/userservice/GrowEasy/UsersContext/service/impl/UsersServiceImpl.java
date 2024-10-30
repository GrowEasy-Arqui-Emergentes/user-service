package com.groweasy.userservice.GrowEasy.UsersContext.service.impl;

import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.CourseDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.entity.Course;
import com.groweasy.userservice.GrowEasy.UsersContext.repository.CoursesRepository;
import com.groweasy.userservice.GrowEasy.UsersContext.service.UsersService;
import com.groweasy.userservice.GrowEasy.Shared.exception.ResourceNotFoundException;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.UserRequestDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.response.UserResponseDto;
import com.groweasy.userservice.GrowEasy.UsersContext.model.entity.User;
import com.groweasy.userservice.GrowEasy.UsersContext.repository.UsersRepository;
import com.groweasy.userservice.GrowEasy.UsersContext.validations.UserValidation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final CoursesRepository coursesRepository;
    private final ModelMapper modelMapper;

    public UsersServiceImpl(UsersRepository usersRepository,CoursesRepository coursesRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.coursesRepository = coursesRepository;
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
        // Buscar usuario por ID y manejar error si no se encuentra
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con el id: " + id));

        // Convertir cursos de User a CourseDto
        List<CourseDto> courses = user.getCourses().stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());

        // Mapear User a UserResponseDto y agregar lista de cursos
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        userResponseDto.setCourses(courses);

        return userResponseDto;
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
    @Override
    public UserResponseDto addCourseToUser(Long userId, Long courseId) {
        // Verificar existencia de usuario y curso
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + userId));
        Course course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + courseId));

        // Agregar el curso al usuario y guardar
        user.getCourses().add(course);
        User updatedUser = usersRepository.save(user);

        return modelMapper.map(updatedUser, UserResponseDto.class);
    }
    @Override
    public List<CourseDto> getUserCourses(Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + userId));

        return user.getCourses().stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }
}
