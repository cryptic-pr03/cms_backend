package com.example.cms.Models;

public class Pic {
    private int venueId;
    private String venuePicUrl;

    public Pic() {

    }

    public Pic(int venueId, String venuePicUrl) {
        this.venueId = venueId;
        this.venuePicUrl = venuePicUrl;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenuePicUrl() {
        return venuePicUrl;
    }

    public void setVenuePicUrl(String venuePicUrl) {
        this.venuePicUrl = venuePicUrl;
    }
}
