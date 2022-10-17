package com.example.cms.Models;

public class WorksFor {
    private int userId;
    private int role;
    private int eventId;

    public WorksFor() {

    }

    public WorksFor(int userId, int role, int eventId) {
        this.userId = userId;
        this.role = role;
        this.eventId = eventId;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
