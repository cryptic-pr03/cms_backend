package com.example.cms.dao;

import com.example.cms.Models.SeatBook;

public interface SeatBookDAO {
    SeatBook addSeatBook(SeatBook newSeatBook) throws CustomException;
    String getTrxnId(int eventId, int seatId) throws CustomException;
}
