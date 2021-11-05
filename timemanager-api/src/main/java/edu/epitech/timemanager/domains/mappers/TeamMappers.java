package edu.epitech.timemanager.domains.mappers;

import edu.epitech.timemanager.domains.dto.teams.TeamDto;
import edu.epitech.timemanager.domains.models.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TeamMappers {
    TeamDto teamToTeamDTO(Team team);
    List<TeamDto> teamsToTeamDTOs(List<Team> teams);
}
