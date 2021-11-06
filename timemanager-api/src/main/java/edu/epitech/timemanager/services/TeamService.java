package edu.epitech.timemanager.services;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;

import java.util.List;

public interface TeamService {
    List<Team> getTeams();
    Team getUserTeam(int userId);
    List<Team> getUserTeams(int userId);

    List<User> getTeamMembers(int teamId);

    boolean addUserToTeam(int userId, int teamId);
    boolean removeUserFromTeam(int userId, int teamId);

    boolean isInTeam(int userId, int teamId);
    boolean isInTheSameTeam(int targetUser, int currentUser);

    Team createTeam(int userId, Team team);
    Team updateTeam(int id, Team team);
    void deleteTeam(int teamId);
}
