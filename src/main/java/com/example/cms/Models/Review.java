package com.example.cms.Models;

public class Review {
    private int reviewId;
    private String reviewData;
    private int userId;
    private int eventId;

    public Review() {

    }

    public Review(int reviewId, String reviewData, int userId, int eventId) {
        this.reviewId = reviewId;
        this.reviewData = reviewData;
        this.userId = userId;
        this.eventId = eventId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewData() {
        return reviewData;
    }

    public void setReviewData(String reviewData) {
        this.reviewData = reviewData;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
