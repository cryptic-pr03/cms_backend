package com.example.cms.dao;

import com.example.cms.Models.SeatBook;

public interface SeatBookDAO {
    SeatBook addSeatBook(SeatBook newSeatBook) throws CustomException;
    int getTrxnId(int eventId, int seatId) ;
}
