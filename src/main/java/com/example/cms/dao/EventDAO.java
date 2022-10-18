package com.example.cms.dao;

import com.example.cms.Models.Event;
import java.util.List;

public interface EventDAO {
    //add an event --> arist manager when logged in can add a new event
    void addEvent(Event newEvent) throws CustomException;

    //update an existing event --> artist manager on logged in and before booking a slot for that event can change the event details
    void updateEvent (int eventId, Event updatedEvent) throws CustomException;

    //delete an existing event --> artist manager on logged in and before booking a slot for that event can delete an event
    void deleteEvent(int eventId) throws CustomException;

    // get list of all events --> visible to everyone
    List<Event> getAllEvents() throws CustomException;
    //get details of a specific event --> visible to everyone

    Event getEventByID(int eventId) throws CustomException;

    // for filtering and sorting on all events by attribute -->visible to everyone
    Event getEventByAttribute(String attributeName, String attributeValue) throws CustomException;
}
