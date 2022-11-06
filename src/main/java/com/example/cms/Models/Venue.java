package com.example.cms.Models;

public class Venue {

    private int venueId;
    private String name;

    private int silverSeats;
    private int goldSeats;
    private int platinumSeats;
    private String city;
    private String landmark;
    private String state;
    private boolean isFunctional;
    private String picSeatMatrixUrl;

    public Venue() {

    }

    public Venue(int venueId, String name, int silverSeat, int goldSeat, int platinumSeat, String city, String landmark, String state, boolean isFunctional, String picSeatMatrixUrl) {
        this.venueId = venueId;
        this.name = name;
        this.silverSeats = silverSeat;
        this.goldSeats = goldSeat;
        this.platinumSeats = platinumSeat;
        this.city = city;
        this.landmark = landmark;
        this.state = state;
        this.isFunctional = isFunctional;
        this.picSeatMatrixUrl = picSeatMatrixUrl;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSilverSeats() {
        return silverSeats;
    }

    public void setSilverSeats(int silverSeats) {
        this.silverSeats = silverSeats;
    }

    public int getGoldSeats() {
        return goldSeats;
    }

    public void setGoldSeats(int goldSeats) {
        this.goldSeats = goldSeats;
    }

    public int getPlatinumSeats() {
        return platinumSeats;
    }

    public void setPlatinumSeats(int platinumSeats) {
        this.platinumSeats = platinumSeats;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isFunctional() {
        return isFunctional;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
    }

    public String getPicSeatMatrixUrl() {
        return picSeatMatrixUrl;
    }

    public void setPicSeatMatrixUrl(String picSeatMatrixUrl) {
        this.picSeatMatrixUrl = picSeatMatrixUrl;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", name='" + name + '\'' +
                ", silverSeat=" + silverSeats +
                ", goldSeat=" + goldSeats +
                ", platinumSeat=" + platinumSeats +
                ", city='" + city + '\'' +
                ", landmark='" + landmark + '\'' +
                ", state='" + state + '\'' +
                ", isFunctional=" + isFunctional +
                ", picSeatMatrixUrl='" + picSeatMatrixUrl + '\'' +
                '}';
    }
}
