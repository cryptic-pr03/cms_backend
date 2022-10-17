package com.example.cms.Models;

import java.sql.Time;

public class Slot {
    private int slotId;
    private int venueId;
    private Time startTime;
    private Time endTime;
    private int price;
    private boolean rentalStatus;

    public Slot() {

    }

    public Slot(int slotId, int venueId, Time startTime, Time endTime, int price, boolean rentalStatus) {
        this.slotId = slotId;
        this.venueId = venueId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.rentalStatus = rentalStatus;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
