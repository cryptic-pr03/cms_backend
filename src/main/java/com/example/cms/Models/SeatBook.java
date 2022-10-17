package com.example.cms.Models;

public class SeatBook {
    private int eventId;
    private int seatId;
    private int transactionId;

    public SeatBook() {

    }

    public SeatBook(int eventId, int seatId, int transactionId) {
        this.eventId = eventId;
        this.seatId = seatId;
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "SeatBook{" +
                "eventId=" + eventId +
                ", seatId=" + seatId +
                ", transactionId=" + transactionId +
                '}';
    }

    public int geteventId() {
        return eventId;
    }

    public void seteventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
