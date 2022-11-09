package com.example.cms.Models;

public class WorksFor {
    private int staffId;

    private int eventId;

    public WorksFor() {

    }

    public WorksFor(int staffId, int role, int eventId) {
        this.staffId = staffId;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "WorksFor{" +
                "userId=" + staffId +
                ", eventId=" + eventId +
                '}';
    }


    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
