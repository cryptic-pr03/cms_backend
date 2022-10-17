package com.example.cms.Models;

import java.sql.Date;

public class Salary {
    private int userId;
    private int role;
    private Date dateOfPayment;
    private int amount;
    private int bonus;
    private Boolean paidStatus;

    public Salary() {

    }

    public Salary(int userId, int role, Date dateOfPayment, int amount, int bonus, Boolean paidStatus) {
        this.userId = userId;
        this.role = role;
        this.dateOfPayment = dateOfPayment;
        this.amount = amount;
        this.bonus = bonus;
        this.paidStatus = paidStatus;
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

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
