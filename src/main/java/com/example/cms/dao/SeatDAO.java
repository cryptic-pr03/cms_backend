package com.example.cms.dao;

import com.example.cms.Models.Seat;

import java.util.List;

public interface SeatDAO {
    Seat getSeat(int SeatId, int venueId) throws CustomException;
    Seat addSeat(Seat newSeat) throws CustomException;
    Seat updateSeat(int SeatId, int venueId, Seat updatedSeat) throws CustomException;
    Boolean deleteSeat(int SeatId, int venueId) throws CustomException;
    List<Seat> getSeatByVenueId(int venueId) throws CustomException;
    List<Seat> getSeatAtVenueByType(int venueId, String seatType) throws CustomException;
}
