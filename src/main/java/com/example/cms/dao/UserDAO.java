package com.example.cms.dao;

import com.example.cms.Models.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    User addUser(User newUser) throws  CustomException;
    User updateUser(User updatedUser, int userId) throws CustomException;
    User getUserById(int userId) throws CustomException;
    User getUserByEmailId(String userEmailId) throws CustomException;
    List<Map<String, Object>> getAllSeatBookingsOfUser(int userId);
}
