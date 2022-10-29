package com.example.cms.dao;

import com.example.cms.Models.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ReviewRepo implements ReviewDAO{

    private static class ReviewMapper implements RowMapper<Review> {

        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            Review review = new Review();
            review.setReviewData(rs.getString("reviewData"));
            review.setReviewId(rs.getInt("reviewId"));
            review.setUserId(rs.getInt("userId"));
            review.setEventId(rs.getInt("eventId"));
            return review;
        }
    }

    private final JdbcTemplate jdbcTemplate;
    public ReviewRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Review addReview(Review newReview) throws CustomException {
        String querySQL = "INSERT INTO Review ( reviewData , eventId , userId) VALUES(?,?,?)";
        int isCreated;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            isCreated = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(querySQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, newReview.getReviewData());
                ps.setInt(2, newReview.getEventId());
                ps.setInt(3, newReview.getUserId());
                return ps;
            }, keyHolder);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0 || keyHolder.getKey() == null) {
            throw new CustomException("Could not insert review");
        }
        newReview.setReviewId(keyHolder.getKey().intValue());
        return newReview;
    }

    @Override
    public Review updateReview(Review updatedReview, int reviewId) throws CustomException {
        String querySQL = "UPDATE Review SET reviewData=? , userId=? , eventId=? WHERE reviewId =?";
        int isUpdated;
        try{
            isUpdated = jdbcTemplate.update(querySQL ,updatedReview.getReviewData() , updatedReview.getUserId() , updatedReview.getEventId() , reviewId );
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isUpdated==0)throw new CustomException("Can not update");
        return updatedReview;
    }

    @Override
    public Boolean deleteReview(int reviewId) throws CustomException {
        String querySQL = "DELETE FROM Review WHERE reviewId=?";
        int isDeleted;
        try{
            isDeleted  = jdbcTemplate.update(querySQL , reviewId);
        }
        catch(Exception e){
            throw new CustomException(e.getMessage());
        }
        return isDeleted !=0;
    }

    @Override
    public List<Review> getReviewsByEvent(int eventId) throws CustomException {
        String querySQL= "SELECT * FROM Review WHERE eventId = ?";

        try{
            List<Review> ls = jdbcTemplate.query(querySQL , new ReviewMapper() , eventId);
            return ls;
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }

    }
}
