package com.example.cms.dao;

import com.example.cms.Models.Review;

import java.util.List;

public interface ReviewDAO {
    Review addReview(Review newReview) throws CustomException;
    Review updateReview(Review updatedReview, int reviewId) throws CustomException;
    Boolean deleteReview(int reviewId) throws  CustomException;
    List<Review> getReviewsByEvent(int eventId) throws CustomException;
}
