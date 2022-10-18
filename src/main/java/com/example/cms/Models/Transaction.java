package com.example.cms.Models;

import java.sql.Date;
import java.sql.Time;

public class Transaction {

    private int transactionId;
    private Date date;
    private Time time;
    private String type;
    private int amount;
    private int userId;
    private int eventId;
    private String transactionImageUrl;

    public Transaction() {

    }
    
    public Transaction(int transactionId, Date date, Time time, String type, int amount, int userId, int eventId, String transactionImageUrl) {
        this.transactionId = transactionId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.amount = amount;
        this.userId = userId;
        this.eventId = eventId;
        this.transactionImageUrl = transactionImageUrl;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String gettransactionImageUrl() {
        return transactionImageUrl;
    }

    public void settransactionImageUrl(String transactionImageUrl) {
        this.transactionImageUrl = transactionImageUrl;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", date=" + date +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", transactionImageUrl='" + transactionImageUrl + '\'' +
                '}';
    }
}
