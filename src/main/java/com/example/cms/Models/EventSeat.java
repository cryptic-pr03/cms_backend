package com.example.cms.Models;

public class EventSeat {
    private int seatId;
    private int eventId;
    private boolean isBooked;
    private int price;

    public EventSeat() {

    }

    public EventSeat(int seatId, int eventId, boolean isBooked, int price) {
        this.seatId = seatId;
        this.eventId = eventId;
        this.isBooked = isBooked;
        this.price = price;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "EventSeatStatus{" +
                "seatId=" + seatId +
                ", eventId=" + eventId +
                ", isBooked=" + isBooked +
                ", price=" + price +
                '}';
    }
}
