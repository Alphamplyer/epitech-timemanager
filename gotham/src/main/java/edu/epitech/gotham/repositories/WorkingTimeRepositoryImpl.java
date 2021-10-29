package edu.epitech.gotham.repositories;

import edu.epitech.gotham.models.WorkingTime;
import edu.epitech.gotham.repositories.mappers.WorkingTimeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class WorkingTimeRepositoryImpl implements WorkingTimeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkingTimeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkingTime> getUserWorkingTimes(int userId, Timestamp start, Timestamp end) {
        final String GET_USER_WORKING_TIMES_QUERY = "SELECT * FROM working_times WHERE user_id = :userId AND start <= :start AND end >= :end";
        return jdbcTemplate.query(
                GET_USER_WORKING_TIMES_QUERY,
                new WorkingTimeRowMapper(),
                new MapSqlParameterSource()
                        .addValue("userId", userId)
                        .addValue("start", start)
                        .addValue("end", end)
        );
    }

    @Override
    public Optional<WorkingTime> getWorkingTimeById(int id) {
        final String GET_WORKING_TIME_QUERY = "SELECT * FROM working_times WHERE id = :id";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        GET_WORKING_TIME_QUERY,
                        new WorkingTimeRowMapper(),
                        new MapSqlParameterSource()
                                .addValue("id", id)
                )
        );
    }

    @Override
    public int createWorkingTime(WorkingTime workingTime, int userId) {
        final String CREATE_WORKING_TIME_QUERY = "INSERT INTO working_times(user_id, start, end) VALUES(:userId, :start, :end)";
        return jdbcTemplate.update(
                CREATE_WORKING_TIME_QUERY,
                new MapSqlParameterSource()
                        .addValue("userId", userId)
                        .addValue("status", workingTime.getStart())
                        .addValue("userId", workingTime.getEnd())
        );
    }

    @Override
    public int updateWorkingTime(WorkingTime workingTime, int id) {
        final String UPDATE_WORKING_TIME_QUERY = "UPDATE working_times SET user_id = :userId, start = :start, end = :end";
        return jdbcTemplate.update(
                UPDATE_WORKING_TIME_QUERY,
                new MapSqlParameterSource()
                        .addValue("status", workingTime.getStart())
                        .addValue("userId", workingTime.getEnd())
                        .addValue("id", id)
        );
    }

    @Override
    public int deleteWorkingTime(int id) {
        final String DELETE_WORKING_TIME_QUERY = "DELETE  FROM working_times WHERE id = :id";
        return jdbcTemplate.update(
                DELETE_WORKING_TIME_QUERY,
                new MapSqlParameterSource()
                        .addValue("id", id)
        );
    }
}
