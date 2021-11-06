package edu.epitech.timemanager.services.impl;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
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
        return teamRepository.findFirstTeamByMembers_Id(userId).orElse(null);
    }

    @Override
    public List<Team> getUserTeams(int userId) {
        return teamRepository.findTeamsByMembers_Id(userId);
    }

    @Override
    public List<User> getTeamMembers(int teamId) {
        boolean isTeamExist = userRepository.existsById(teamId);

        if (!isTeamExist)
            return null;

        return teamRepository.getTeamMembers(teamId);
    }

    @Override
    public boolean addUserToTeam(int userId, int teamId) {
        boolean isUserExist = userRepository.existsById(userId);
        boolean isTeamExist = userRepository.existsById(userId);

        if (!isUserExist || !isTeamExist)
            return false;

        teamRepository.addTeamMember(userId, teamId);
        return true;
    }

    @Override
    public boolean removeUserFromTeam(int userId, int teamId) {
        boolean isUserExist = userRepository.existsById(userId);
        boolean isTeamExist = userRepository.existsById(userId);

        if (!isUserExist || !isTeamExist)
            return false;

        teamRepository.deleteTeamMember(userId, teamId);
        return true;
    }

    @Override
    public boolean isInTeam(int userId, int teamId) {
        return teamRepository.findUserInTeam(userId, teamId).isPresent();
    }

    @Override
    public boolean isInTheSameTeam(int targetUserId, int currentUserId) {
        if (targetUserId == currentUserId)
            return true;

        return teamRepository.isInTheSameTeam(targetUserId, currentUserId).isPresent();
    }

    @Override
    public Team createTeam(int userId, Team team) {
        boolean isUserExist = userRepository.existsById(userId);

        if (!isUserExist)
            return null;

        Team persistedTeam = teamRepository.save(team);
        teamRepository.addTeamMember(userId, persistedTeam.getId());
        return persistedTeam;
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
