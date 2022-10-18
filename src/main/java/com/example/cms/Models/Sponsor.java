package com.example.cms.Models;

public class Sponsor {
    private int eventId;
    private String sponsorName;

    public Sponsor() {

    }

    public Sponsor(int eventId, String sponsorName) {
        this.eventId = eventId;
        this.sponsorName = sponsorName;
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "eventId=" + eventId +
                ", sponsorName='" + sponsorName + '\'' +
                '}';
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
