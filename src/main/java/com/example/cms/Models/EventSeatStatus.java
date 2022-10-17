package com.example.cms.Models;

public class EventSeatStatus {
    private int seatId;
    private int venueId;
    private int eventId;
    private boolean status;
    private int price;

    public EventSeatStatus() {

    }

    public EventSeatStatus(int seatId, int venueId, int eventId, boolean status, int price) {
        this.seatId = seatId;
        this.venueId = venueId;
        this.eventId = eventId;
        this.status = status;
        this.price = price;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
