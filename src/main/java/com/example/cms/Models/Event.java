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
    private String logoUrl;

    public Event() {

    }

    public Event(int eventId, String name, Time startTime, Time endTime, int ageLimit, Date eventDate, String logoUrl) {
        this.eventId = eventId;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ageLimit = ageLimit;
        this.eventDate = eventDate;
        this.logoUrl = logoUrl;
    }

    public int getEventId(){
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName(){
        return name;
    }

    public void setEventName(String name){
        this.name = name;
    }

    public Time getEventStartTime(){
        return startTime;
    }

    public void setEventStartTime(Time startTime){
        this.startTime = startTime;
    }

    public Time getEventEndTime(){
        return endTime;
    }

    public void setEventEndTime(Time endTime){
        this.endTime = endTime;
    }

    public Date getEventDate(){
        return eventDate;
    }

    public void setEventDate(Date eventDate){
        this.eventDate = eventDate;
    }

    public int getEventAge(){
        return ageLimit;
    }

    public void setEventAge(int age){
        this.ageLimit = age;
    }

    public String getEventLogoUrl(){
        return logoUrl;
    }

    public void setEventLogoUrl(String logoUrl){
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
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
