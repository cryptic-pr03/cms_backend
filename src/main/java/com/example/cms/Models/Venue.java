package com.example.cms.Models;

public class Venue {

    private int venueId;
    private String name;
    private int capacity;
    private String city;
    private String landmark;
    private String state;
    private boolean isFunctional;
    private String seatMatrixDescription;
    private String picSeatMatrixUrl;

    public Venue() {

    }

    public Venue(
            int venueId, String name, int capacity, String city, String landmark,
            String state, boolean isFunctional, String seatMatrixDescription, String picSeatMatrixUrl
    ){
        this.venueId = venueId;
        this.name = name;
        this.capacity = capacity;
        this.city = city;
        this.landmark = landmark;
        this.state = state;
        this.isFunctional = isFunctional;
        this.seatMatrixDescription = seatMatrixDescription;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public void setAvailable(boolean availability) {
        isFunctional = availability;
    }

    public String getSeatMatrixDescription() {
        return seatMatrixDescription;
    }

    public void setSeatMatrixDescription(String seatMatrixDescription) {
        this.seatMatrixDescription = seatMatrixDescription;
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
                ", capacity=" + capacity +
                ", city='" + city + '\'' +
                ", landmark='" + landmark + '\'' +
                ", state='" + state + '\'' +
                ", isFunctional=" + isFunctional +
                ", seatMatrixDescription='" + seatMatrixDescription + '\'' +
                ", picSeatMatrixUrl='" + picSeatMatrixUrl + '\'' +
                '}';
    }
}
