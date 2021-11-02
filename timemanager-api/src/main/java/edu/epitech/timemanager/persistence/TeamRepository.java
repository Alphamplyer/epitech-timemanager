package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query("SELECT t.id, t.name, t.description FROM teams t, team_members tm WHERE tm.user_id = :userId AND t.id = tm.team_id")
    List<Team> findUserTeams(int userId);

    @Query("DELETE FROM team_members tm WHERE tm.user_id = :userId")
    void deleteTeamMember(int userId);
}
