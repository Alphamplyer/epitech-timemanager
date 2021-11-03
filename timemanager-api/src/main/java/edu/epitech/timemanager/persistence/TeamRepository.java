package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findTeamsByMembers_Id(int userId);

    @Query(nativeQuery = true, value = "DELETE FROM team_members tm WHERE tm.user_id = ?1")
    void deleteTeamMember(int userId);
}
