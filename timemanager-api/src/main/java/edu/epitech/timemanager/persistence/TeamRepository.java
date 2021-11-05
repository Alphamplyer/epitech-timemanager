package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findFirstTeamByMembers_Id(int userId);
    List<Team> findTeamsByMembers_Id(int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM users u, teams_members tm WHERE tm.team_id = ?1 AND tm.user_id = u.id")
    List<User> getTeamMembers(int teamId);

    @Query(nativeQuery = true, value = "INSERT INTO teams_members(user_id, team_id) VALUES (?1, ?2)")
    void addTeamMember(int userId, int teamId);

    @Query(nativeQuery = true, value = "DELETE FROM teams_members tm WHERE tm.user_id = ?1 AND tm.team_id = ?2")
    void deleteTeamMember(int userId, int teamId);
}
