package com.example.cms.Models;

public class TakesPlace {
    private int venueId;
    private int slotId;
    private int eventId;

    public TakesPlace() {

    }

    public TakesPlace(int venueId, int slotId, int eventId) {
        this.venueId = venueId;
        this.slotId = slotId;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "TakesPlace{" +
                "venueId=" + venueId +
                ", slotId=" + slotId +
                ", eventId=" + eventId +
                '}';
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
