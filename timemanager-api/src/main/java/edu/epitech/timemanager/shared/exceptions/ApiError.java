package edu.epitech.timemanager.shared.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    HttpStatus code;
    String message;
    List<String> errors;

    public ApiError(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
