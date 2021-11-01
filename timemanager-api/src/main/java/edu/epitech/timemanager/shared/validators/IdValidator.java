package edu.epitech.timemanager.shared.validators;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class IdValidator {
    public static boolean isValid(Integer id, List<String> errors) {
        boolean result = id != null && id > 0;
        if (result)
            errors.add("An ID must be a non null and positive number");
        return result;
    }
}
