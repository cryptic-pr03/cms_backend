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

@Repository
public class UserRepo implements UserDAO{

    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setUserFirstName(rs.getString("firstName"));
            user.setUserLastName(rs.getString("lastName"));
            user.setUserEmail(rs.getString("email"));
            user.setUserPassword(rs.getString("password"));
            user.setUserContactNo(rs.getString("contactNo"));
            user.setUserGender(rs.getString("gender"));
            user.setUserDOB(rs.getDate("DOB"));
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
                ps.setString(1, newUser.getUserFirstName());
                ps.setString(2, newUser.getUserLastName());
                ps.setString(3, newUser.getUserEmail());
                ps.setString(4, newUser.getUserPassword());
                ps.setString(5, newUser.getUserContactNo());
                ps.setString(6, newUser.getUserGender());
                ps.setDate(7, newUser.getUserDOB());

                return ps;
            }, keyHolder);

        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0 || keyHolder.getKey() == null){
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
            isUpdated = jdbcTemplate.update(sql, updatedUser.getUserFirstName(), updatedUser.getUserLastName(), updatedUser.getUserEmail(), updatedUser.getUserPassword(),
                    updatedUser.getUserContactNo(), updatedUser.getUserGender(), updatedUser.getUserDOB(), userId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isUpdated == 0){
            throw new CustomException("Could not update user");
        }
        return updatedUser;
    }

    @Override
    public User getUserById(int userId) throws CustomException {
        String sql = "SELECT * FROM User WHERE userId = ?";

        try{
            List<User> ls = jdbcTemplate.query(sql, new UserMapper(), userId);
            if (ls.size() == 0) {
                return null;
            } else {
                return ls.get(0);
            }
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public User getUserByEmailId(String userEmailId) throws CustomException {
        String sql = "SELECT * FROM User WHERE email = ?";

        try{
            List<User> ls = jdbcTemplate.query(sql, new UserMapper(), userEmailId);
            if (ls.size() == 0) {
                return null;
            } else {
                return ls.get(0);
            }
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
