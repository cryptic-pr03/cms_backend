package com.example.cms.Models;

import java.sql.Date;
import java.sql.Time;

public class Staff {
    private int userId;
    private int role;
    private int groupNumber;
    private int salary;
    private Time timings;
    private int venueId;
    private Date joiningDate;
//    private Date leavingDate;


    public Staff() {

    }

    public Staff(int userId, int role, int groupNumber, int salary, Time timings, int venueId, Date joiningDate) {
        this.userId = userId;
        this.role = role;
        this.groupNumber = groupNumber;
        this.salary = salary;
        this.timings = timings;
        this.venueId = venueId;
        this.joiningDate = joiningDate;
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

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Time getTimings() {
        return timings;
    }

    public void setTimings(Time timings) {
        this.timings = timings;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }
}
