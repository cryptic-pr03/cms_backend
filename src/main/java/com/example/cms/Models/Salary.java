package com.example.cms.Models;

import java.sql.Timestamp;

public class Salary {
    private int staffId;
    private Timestamp timeOfPayment;
    private int bonus;
    private Boolean paidStatus;

    public Salary() {

    }

    public Salary(int staffId, Timestamp timeOfPayment, int bonus, Boolean paidStatus) {
        this.staffId = staffId;
        this.timeOfPayment = timeOfPayment;
        this.bonus = bonus;
        this.paidStatus = paidStatus;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "staffId=" + staffId +
                ", timeOfPayment=" + timeOfPayment +
                ", bonus=" + bonus +
                ", paidStatus=" + paidStatus +
                '}';
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Timestamp getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(Timestamp timeOfPayment) {
        this.timeOfPayment = timeOfPayment;

    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public Boolean getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(Boolean paidStatus) {
        this.paidStatus = paidStatus;
    }
}
