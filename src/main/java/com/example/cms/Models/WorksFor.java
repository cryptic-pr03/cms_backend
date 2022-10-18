package com.example.cms.Models;

public class WorksFor {
    private int userId;

    private int eventId;

    public WorksFor() {

    }

    public WorksFor(int userId, int role, int eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "WorksFor{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                '}';
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
