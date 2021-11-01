package edu.epitech.timemanager.shared.validators;

import java.util.List;

public class EmailValidator {
    public static boolean isValid(String email, List<String> errors) {
        boolean result = org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
        if (!result)
            errors.add("Invalid Email");
        return result;
    }
}
