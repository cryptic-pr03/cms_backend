package com.example.cms.dao;

import com.example.cms.Models.TypeUser;

import java.util.List;

public interface TypeUserDAO {
    List<Integer> getRolesByUserId(int userId) throws CustomException;
    TypeUser addUserRole(TypeUser newTypeUser) throws CustomException;
}
