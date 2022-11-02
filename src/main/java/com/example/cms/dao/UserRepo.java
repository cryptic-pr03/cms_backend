package com.example.cms.dao;

import com.example.cms.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepo implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setContactNo(rs.getString("contactNo"));
            user.setGender(rs.getString("gender"));
            user.setDOB(rs.getDate("DOB"));
            return user;
        }
    }

    @Override
    public User addUser(User newUser) throws CustomException {
        String sql = "INSERT INTO User(firstName, lastName, email, password, contactNo, gender, DOB) VALUES(?,?,?,?,?,?,?)";

        int isCreated;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {

            isCreated = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, newUser.getFirstName());
                ps.setString(2, newUser.getLastName());
                ps.setString(3, newUser.getEmail());
                ps.setString(4, newUser.getPassword());
                ps.setString(5, newUser.getContactNo());
                ps.setString(6, newUser.getGender());
                ps.setDate(7, newUser.getDOB());

                return ps;
            }, keyHolder);

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        if (isCreated == 0 || keyHolder.getKey() == null) {
            throw new CustomException("User not inserted");
        }
        newUser.setUserId(keyHolder.getKey().intValue());
        return newUser;
    }

    @Override
    public User updateUser(User updatedUser, int userId) throws CustomException {
        String sql = "UPDATE User " +
                "SET firstName = ?, lastName = ?, email = ?, password = ?, contactNo = ?, gender = ?, DOB = ?" +
                "WHERE userId = ?";

        int isUpdated;
        try {
            isUpdated = jdbcTemplate.update(sql, updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail(), updatedUser.getPassword(),
                    updatedUser.getContactNo(), updatedUser.getGender(), updatedUser.getDOB(), userId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        if (isUpdated == 0) {
            throw new CustomException("Could not update user");
        }
        return updatedUser;
    }

    @Override
    public User getUserById(int userId) throws CustomException {
        String sql = "SELECT * FROM User WHERE userId = ?";

        try {
            List<User> ls = jdbcTemplate.query(sql, new UserMapper(), userId);
            if (ls.size() == 0) {
                return null;
            } else {
                return ls.get(0);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public User getUserByEmailId(String userEmailId) throws CustomException {
        String sql = "SELECT * FROM User WHERE email = ?";

        try {
            List<User> ls = jdbcTemplate.query(sql, new UserMapper(), userEmailId);
            if (ls.size() == 0) {
                return null;
            } else {
                return ls.get(0);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getAllSeatBookingsOfUser(int userId) {
        String sql =
                "SELECT eventId, name AS EventName, startTime, endTime, ageLimit, eventDate, " +
                "description, logoUrl, transactionId, date AS transactionDate, time AS transactionTime " +
                "type FROM Event e, Transaction t WHERE t.userId = ? AND e.eventId = t.eventId " +
                "AND type LIKE 'AUDIENCE'";

        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, userId);

        for(Map<String, Object> obj : res) {
            int tId = (int) obj.get("transactionId");
            int eId = (int) obj.get("eventId");

            String sqlQuery = "SELECT es.seatId, es.price FROM EventSeat es, SeatBook sb " +
                              "WHERE sb.eventId = ? AND sb.transactionId = ? AND " +
                              "sb.seatId = es.seatId AND es.eventId = sb.eventId";

            obj.put("seats", jdbcTemplate.queryForList(sqlQuery, eId, tId));
        }

        return res;
    }
}
