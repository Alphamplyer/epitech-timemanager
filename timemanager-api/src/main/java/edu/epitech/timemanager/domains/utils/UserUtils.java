package edu.epitech.timemanager.domains.utils;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import edu.epitech.timemanager.services.TeamService;

public class UserUtils {
    public static boolean isTheSameUser(User user, int userId) {
        return user.getId() == userId;
    }

    public static boolean isGlobalManager(User user) {
        return user.getRole().equals(Role.GLOBAL_MANAGER);
    }

    public static boolean isManager(User user) {
        return user.getRole().equals(Role.MANAGER);
    }

    public static boolean isEmployee(User user) {
        return user.getRole().equals(Role.EMPLOYEE);
    }

    public static boolean isManagerOfUserTeam(User user, int userId, TeamService teamService) {
        return UserUtils.isManager(user) && teamService.isInTheSameTeam(user.getId(), userId);
    }
}
