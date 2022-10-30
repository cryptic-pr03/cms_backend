package com.example.cms.dao;

import com.example.cms.Models.TypeUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeUserRepo implements TypeUserDAO{

    private final JdbcTemplate jdbcTemplate;

    public TypeUserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Integer> getRolesByUserId(int userId) throws CustomException {
        String sql = "SELECT role FROM TypeUser WHERE userId = ?";

        try {
            return jdbcTemplate.queryForList(sql, Integer.class, userId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public TypeUser addUserRole(TypeUser newTypeUser) throws CustomException {
        String sql = "INSERT INTO TypeUser(userId, role) VALUES(?,?)";

        int isCreated;

        try{
            isCreated = jdbcTemplate.update(sql, newTypeUser.getUserId(), newTypeUser.getRole());
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0){
            throw new CustomException("Could not assign role to user with id : " + newTypeUser.getUserId());
        }
        return newTypeUser;
    }
}
