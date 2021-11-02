package edu.epitech.timemanager.shared.validators;

import java.util.List;

public class UsernameValidator {
    public static boolean isValid(String s, List<String> errors) {
        boolean result = true;

        if (s.length() < 2 || s.length() > 50) {
            errors.add("Username length should be between 2 and 50 characters.");
            result = false;
        }

        return result;
    }
}
