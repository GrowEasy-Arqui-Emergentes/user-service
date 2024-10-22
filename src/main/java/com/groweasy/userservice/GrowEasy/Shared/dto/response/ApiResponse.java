package com.groweasy.userservice.GrowEasy.Shared.dto.response;

import com.groweasy.userservice.GrowEasy.Shared.dto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private Status status;
    private T data;
}
