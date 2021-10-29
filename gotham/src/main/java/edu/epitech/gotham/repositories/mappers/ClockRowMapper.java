package edu.epitech.gotham.repositories.mappers;

import edu.epitech.gotham.models.Clock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClockRowMapper implements RowMapper<Clock> {

    @Override
    public Clock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Clock clock = new Clock();

        clock.setUserId(rs.getInt("user_id"));
        clock.setTime(rs.getTimestamp("time"));
        clock.setStatus(rs.getBoolean("status"));

        return clock;
    }
}
