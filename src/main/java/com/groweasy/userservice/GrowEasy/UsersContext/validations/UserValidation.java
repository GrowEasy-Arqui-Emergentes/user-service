package com.groweasy.userservice.GrowEasy.UsersContext.validations;

import com.groweasy.userservice.GrowEasy.Shared.exception.ValidationException;
import com.groweasy.userservice.GrowEasy.UsersContext.model.dto.request.UserRequestDto;

public class UserValidation {
    public static void validateUserFirstName(String firstName) {
        if (firstName.length() > 50) {
            throw new ValidationException("El nombre no puede tener más de 50 caracteres");
        }
    }

    public static void validateUserLastName(String lastName) {
        if (lastName.length() > 50) {
            throw new ValidationException("El apellido no puede tener más de 50 caracteres");
        }
    }

    public static void validateUserEmail(String email) {
        if (!email.matches("^(.+)@(.+)$")) {
            throw new ValidationException("El email debe ser válido");
        }
    }

    public static void validateUserPassword(String password) {
        if (password.length() < 5) {
            throw new ValidationException("La contraseña debe tener al menos 5 caracteres");
        }
        if (!password.matches(".*\\d.*")) {
            throw new ValidationException("La contraseña debe contener al menos un número");
        }
    }

    public static void ValidateUser(UserRequestDto userRequestDto) {
        if (userRequestDto.getFirstName().isEmpty()){
            throw new ValidationException("El nombre es obligatorio");
        }
        if (userRequestDto.getLastName().isEmpty()){
            throw new ValidationException("El apellido es obligatorio");
        }
        if (userRequestDto.getEmail().isEmpty()){
            throw new ValidationException("El email es obligatorio");
        }
        if (userRequestDto.getPassword().isEmpty()){
            throw new ValidationException("La contraseña es obligatoria");
        }

        validateUserFirstName(userRequestDto.getFirstName());
        validateUserLastName(userRequestDto.getLastName());
        validateUserEmail(userRequestDto.getEmail());
        validateUserPassword(userRequestDto.getPassword());
    }
}
