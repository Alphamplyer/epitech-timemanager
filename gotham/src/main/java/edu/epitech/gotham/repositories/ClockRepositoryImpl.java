package edu.epitech.gotham.repositories;

import edu.epitech.gotham.models.Clock;
import edu.epitech.gotham.repositories.mappers.ClockRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClockRepositoryImpl implements ClockRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClockRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Clock> getUserClock(int userId) {
        final String GET_USER_CLOCK_QUERY = "SELECT * FROM clocks WHERE id = :id";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        GET_USER_CLOCK_QUERY,
                        new ClockRowMapper(),
                        new MapSqlParameterSource()
                                .addValue("user_id", userId)
                )
        );
    }

    @Override
    public int createClock(Clock clock, int userId) {
        final String CREATE_USER_CLOCK_QUERY = "INSERT INTO clocks(user_id, time, status) VALUES(:userId, :time, :status)";
        return jdbcTemplate.update(
                CREATE_USER_CLOCK_QUERY,
                new MapSqlParameterSource()
                        .addValue("time", clock.getTime())
                        .addValue("status", clock.getStatus())
                        .addValue("userId", userId)
        );
    }

    @Override
    public int updateClock(Clock clock, int userId) {
        final String UPDATE_USER_CLOCK_QUERY = "UPDATE clocks SET user_id = :uerId, time = :time, status = :status";
        return jdbcTemplate.update(
                UPDATE_USER_CLOCK_QUERY,
                new MapSqlParameterSource()
                        .addValue("time", clock.getTime())
                        .addValue("status", clock.getStatus())
                        .addValue("userId", userId)
        );
    }
}
