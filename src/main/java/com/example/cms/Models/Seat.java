package com.example.cms.Models;

public class Seat {
    private int seatId;
    private int venueId;
    private String seatType;

    public Seat() {

    }

    public Seat(int seatId, int venueId, String seatType) {
        this.seatId = seatId;
        this.venueId = venueId;
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", venueId=" + venueId +
                ", seatType='" + seatType + '\'' +
                '}';
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getseatType() {
        return seatType;
    }

    public void setseatType(String seatType) {
        this.seatType = seatType;
    }
}
