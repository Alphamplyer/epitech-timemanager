package edu.epitech.timemanager.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiErrorException extends RuntimeException {
    private final ApiError apiError;

    public ApiErrorException(String message, HttpStatus code, List<String> errors) {
        super(message);
        apiError = new ApiError(code, message, errors);
    }

    public ApiError getApiError() {
        return apiError;
    }
}
