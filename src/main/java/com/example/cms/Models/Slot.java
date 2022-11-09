package com.example.cms.Models;

import java.sql.Date;
import java.sql.Time;

public class Slot {
    private int slotId;
    private int venueId;
    private Time startTime;
    private Time endTime;
    private Date slotDate;
    private int price;
    private boolean isRented;
    
    public Slot() {

    }

    public Slot(int slotId, int venueId, Time startTime, Time endTime, Date slotDate, int price, boolean isRented) {
        this.slotId = slotId;
        this.venueId = venueId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotDate = slotDate;
        this.price = price;
        this.isRented = isRented;
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

    public Date getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(Date slotDate) {
        this.slotDate = slotDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getIsRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        this.isRented = rented;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotId=" + slotId +
                ", venueId=" + venueId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", slotDate=" + slotDate +
                ", price=" + price +
                ", isRented=" + isRented +
                '}';
    }
}
