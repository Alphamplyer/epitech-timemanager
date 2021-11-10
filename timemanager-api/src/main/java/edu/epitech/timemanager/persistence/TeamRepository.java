package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findFirstTeamByMembers_Id(int userId);
    List<Team> findTeamsByMembers_Id(int userId);

    @Query(nativeQuery = true, value = "SELECT u.* FROM users u, teams_members tm WHERE tm.members_id = ?1 AND tm.members_id = ?2")
    Optional<User> findUserInTeam(int userId, int teamId);

    @Query(nativeQuery = true, value = "SELECT count(tm.*) FROM teams_members tm WHERE tm.members_id = ?1 AND tm.teams_id IN (SELECT teams_id FROM teams_members WHERE tm.members_id = ?2)")
    Optional<Team> isInTheSameTeam(int targetUserId, int currentUserId);

    @Query(nativeQuery = true, value = "SELECT u.* FROM users u, teams_members tm WHERE tm.members_id = ?1 AND tm.members_id = u.id")
    List<User> getTeamMembers(int teamId);

    @Query(nativeQuery = true, value = "SELECT wt.* FROM working_times wt WHERE wt.user_id IN (SELECT user_id FROM teams_members WHERE teams_id = ?1)")
    List<WorkingTime> getTeamWorkingTimes(int teamId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM teams_members tm WHERE tm.members_id = ?1")
    void deleteUserFromTeams(int userId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM teams_members tm WHERE tm.members_id = ?1 AND tm.teams_id = ?2")
    void deleteTeamMember(int userId, int teamId);
}
