package com.example.cms.Models;

public class Seat {
    private int seatId;
    private int venueId;
    private String type;

    public Seat() {

    }

    public Seat(int seatId, int venueId, String type) {
        this.seatId = seatId;
        this.venueId = venueId;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
