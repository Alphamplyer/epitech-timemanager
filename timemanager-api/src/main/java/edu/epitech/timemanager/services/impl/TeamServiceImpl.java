package edu.epitech.timemanager.services.impl;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import edu.epitech.timemanager.domains.utils.exceptions.ForbiddenException;
import edu.epitech.timemanager.domains.utils.exceptions.NotFoundException;
import edu.epitech.timemanager.persistence.TeamRepository;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.services.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getUserTeam(int userId) {
        Team team = teamRepository.findFirstTeamByMembers_Id(userId).orElse(null);
        if (team == null)
            throw new NotFoundException("Team not found!");
        return team;
    }

    @Override
    public List<Team> getUserTeams(int userId) {
        return teamRepository.findTeamsByMembers_Id(userId);
    }

    @Override
    public List<User> getTeamMembers(int teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team == null)
            throw new NotFoundException("Team not found!");
        return team.getMembers();
    }

    @Override
    public List<WorkingTime> getTeamWorkingTimes(int teamId) {
        boolean isTeamExist = userRepository.existsById(teamId);
        if (!isTeamExist)
            throw new NotFoundException("Team not found!");
        return teamRepository.getTeamWorkingTimes(teamId);
    }

    @Override
    public boolean addUserToTeam(int userId, int teamId) {
        User user = userRepository.findById(userId).orElse(null);
        Team team = teamRepository.findById(teamId).orElse(null);

        if (user == null)
            throw new NotFoundException("User not found!");

        if (team == null)
            throw new NotFoundException("Team not found!");

        if (teamRepository.countNumberOfTimesTheUserIsInATeam(userId) > 0 && user.getRole() == Role.EMPLOYEE)
            throw new ForbiddenException("Employee cannot have more than one team!");

        team.addMember(user);
        teamRepository.save(team);
        return true;
    }

    @Override
    public boolean removeUserFromTeam(int userId, int teamId) {
        User user = userRepository.findById(userId).orElse(null);
        Team team = teamRepository.findById(teamId).orElse(null);

        if (user == null)
            throw new NotFoundException("User not found!");

        if (team == null)
            throw new NotFoundException("Team not found!");

        team.removeMember(user);
        teamRepository.save(team);
        return true;
    }

    @Override
    public boolean isInATeam(int userId) {
        return teamRepository.countNumberOfTimesTheUserIsInATeam(userId) > 0;
    }

    @Override
    public boolean isInTeam(int userId, int teamId) {
        return teamRepository.findUserInTeam(userId, teamId).isPresent();
    }

    @Override
    public boolean isInTheSameTeam(int targetUserId, int currentUserId) {
        if (targetUserId == currentUserId)
            return true;

        return teamRepository.countTheNumberOfTimesUsersAreOnTheSameTeam(targetUserId, currentUserId) > 0;
    }

    @Override
    public Team createTeam(int userId, Team team) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null)
            return null;

        team.getMembers().add(user);
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(int id, Team team) {
        Team persistedTeam = teamRepository.findById(id).orElse(null);

        if (persistedTeam == null)
            return null;

        persistedTeam.setName(team.getName());
        persistedTeam.setDescription(team.getDescription());
        return teamRepository.save(persistedTeam);
    }

    @Override
    public void deleteTeam(int teamId) {
        teamRepository.deleteById(teamId);
    }
}
