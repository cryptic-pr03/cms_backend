package com.example.cms.Models;

public class SeatBook {
    private int venueId;
    private int seatId;
    private int transactionId;

    public SeatBook() {

    }

    public SeatBook(int venueId, int seatId, int transactionId) {
        this.venueId = venueId;
        this.seatId = seatId;
        this.transactionId = transactionId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
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
