package com.example.cms.Controllers;

import com.example.cms.Models.Review;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.ReviewDAO;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    ReviewDAO reviewDAO;

    public ReviewController(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @GetMapping("/event/{eventId}")
    public List<Review> getReviewsByEvent(@PathVariable("eventId") int eventId) throws CustomException {
        try {
            List<Review> reviews = reviewDAO.getReviewsByEvent(eventId);
            //if(reviews.size()==0)throw new CustomException("No reviews found!");
            return reviews;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{reviewId}")
    public Boolean deleteReview(@PathVariable("reviewId") int reviewId) throws CustomException {
        try {
            return reviewDAO.deleteReview(reviewId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) throws CustomException {
        try {
            Review addedReview = reviewDAO.addReview(review);
            return addedReview;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping
    public Review updateReview(@RequestBody Review review) throws CustomException {
        try {
            Review updatedReview = reviewDAO.updateReview(review, review.getReviewId());
            return updatedReview;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}