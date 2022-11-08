package com.example.cms.Models;

import java.sql.Date;
import java.sql.Time;

public class Event {

    private int eventId;
    private String name;
    private Time startTime;
    private Time endTime;
    private int ageLimit;
    private Date eventDate;
    private String description;
    private String logoUrl;

    public Event() {

    }

    public Event(int eventId, String name, Time startTime, Time endTime, int ageLimit, Date eventDate, String eventDesc, String logoUrl) {
        this.eventId = eventId;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ageLimit = ageLimit;
        this.eventDate = eventDate;
        this.description = eventDesc;
        this.logoUrl = logoUrl;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int age) {
        this.ageLimit = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ageLimit=" + ageLimit +
                ", eventDate=" + eventDate +
                ", description='" + description + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
