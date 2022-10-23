package com.example.cms.dao;

import com.example.cms.Models.EventSeat;

import java.util.List;

public interface EventSeatDAO {

    // get the details of seats of that event --> used by audience while selecting seats for booking for an event
    // SUGGEST : what need do we have of venueID
    List<EventSeat> getEventSeatDetails(int eventId) throws CustomException;

    // IDK WHEN TO USE
    EventSeat getSeat(int seatId, int eventId) throws CustomException;

    // if pricing in changed then it is required
    EventSeat updateSeat(int seatId, int eventId, EventSeat newEventSeat) throws CustomException;

    // books seats --> only after verifying if transaction id done
    // change status of seatId
    // returns the details of booked seats
    List<EventSeat> bookSeats(List<Integer> seatIds, int eventId) throws CustomException;

    // when a new event is registered and payment is done (by artist manager)
    // add the corresponding venue's seatMatrix in EventSeat all to unbooked
    void initEventSeats(int eventId) throws CustomException;

    //  OPTIONAL --> done automatically
    //when an event is done after 1 week delete the corresponding seat to avoid populating the database
    // as it is not used later anywhere
    void deleteEventSeats (int eventId) throws CustomException;

}
