package com.example.cms.Models;

public class TypeUser {

    private int userId;
    private int role;

    public TypeUser() {

    }

    public TypeUser(int userId, int role) {
        this.userId = userId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
