package edu.epitech.timemanager.shared.validators;

import java.util.List;

public class NameValidator {
    public static boolean isValid(String str, List<String> errors) {
        if (str == null || str.isEmpty()) {
            errors.add("Name must not be null or empty");
            return false;
        }
        return true;
    }
}
