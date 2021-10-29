package edu.epitech.gotham.repositories.mappers;

import edu.epitech.gotham.models.WorkingTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingTimeRowMapper implements RowMapper<WorkingTime> {

    @Override
    public WorkingTime mapRow(ResultSet rs, int rowNum) throws SQLException {
        WorkingTime workingTime = new WorkingTime();

        workingTime.setId(rs.getInt("id"));
        workingTime.setStart(rs.getTimestamp("start"));
        workingTime.setEnd(rs.getTimestamp("end"));
        workingTime.setUserId(rs.getInt("user_id"));

        return workingTime;
    }
}
