package edu.epitech.timemanager.shared.validators;

import edu.epitech.timemanager.domains.usescases.users.queries.get_all_users.GetAllUsersQuery;

import java.util.List;

import static java.lang.String.format;

public class NumberValidator {

    public static boolean isValid(int number, Integer minInclusive, Integer maxInclusive, List<String> errors) {
        return isValidIfGreaterThan(number, minInclusive, errors) && isValidIfLessThan(number, maxInclusive, errors);
    }

    public static boolean isValidIfGreaterThan(int number, Integer minInclusive, List<String> errors) {
        if (minInclusive == null) return true;
        boolean result = number >= minInclusive;
        if (!result)
            errors.add(format("Number should be greater than %s", minInclusive));
        return result;
    }

    public static boolean isValidIfLessThan(int number, Integer maxInclusive, List<String> errors) {
        if (maxInclusive == null) return true;
        boolean result = number <= maxInclusive;
        if (!result)
            errors.add(format("Number should be smaller than %s", maxInclusive));
        return result;
    }

    public static boolean isValidIfPositive(int number, List<String> errors) {
        return isValidIfGreaterThan(number, 0, errors);
    }

    public static boolean isValidIfPositiveOrNull(Integer number, List<String> errors) {
        if (number == null) return true;
        return isValidIfPositive(number, errors);
    }
}
