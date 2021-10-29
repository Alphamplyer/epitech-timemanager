package edu.epitech.gotham.repositories;

import edu.epitech.gotham.models.User;
import edu.epitech.gotham.repositories.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> findAll() {
        String GET_ALL_USERS_QUERY = "SELECT * FROM users";
        return jdbcTemplate.query(GET_ALL_USERS_QUERY, new UserRowMapper());
    }

    @Override
    public Optional<User> findById(int id) {
        String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = :id";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        GET_USER_BY_ID_QUERY,
                        new UserRowMapper(),
                        new MapSqlParameterSource()
                                .addValue("id", id)
                )
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = :email";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        GET_USER_BY_EMAIL,
                        new UserRowMapper(),
                        new MapSqlParameterSource()
                                .addValue("email", email)
                )
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username = :username";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        GET_USER_BY_USERNAME,
                        new UserRowMapper(),
                        new MapSqlParameterSource()
                                .addValue("username", username)
                )
        );
    }

    @Override
    public int save(User user) {
        String INSERT_USER_QUERY = "INSERT INTO users(id, username, email) VALUES (:id, :username, :email)";
        return jdbcTemplate.update(
                INSERT_USER_QUERY,
                new MapSqlParameterSource()
                        .addValue("username", user.getUsername())
                        .addValue("email", user.getEmail())
        );
    }

    @Override
    public int update(User user, int id) {
        String UPDATE_USER_QUERY = "UPDATE users SET username = :username, email = :email WHERE id = :id";
        return jdbcTemplate.update(
                UPDATE_USER_QUERY,
                new MapSqlParameterSource()
                        .addValue("username", user.getUsername())
                        .addValue("email", user.getEmail())
                        .addValue("id", id)
        );
    }

    @Override
    public int delete(int id) {
        String DELETE_USER_QUERY = "DELETE FROM users WHERE id = :id";
        return jdbcTemplate.update(
                DELETE_USER_QUERY,
                new MapSqlParameterSource()
                        .addValue("id", id)
        );
    }
}
