package com.example.cms.dao;

import com.example.cms.Models.Event;

import java.util.List;
import java.util.Map;

public interface EventDAO {
    //add an event --> arist manager when logged in can add a new event
    Event addEvent(Event newEvent) throws CustomException;

    //update an existing event --> artist manager on logged in and before booking a slot for that event can change the event details
    Event updateEvent(int eventId, Event updatedEvent) throws CustomException;

    //delete an existing event --> artist manager on logged in and before booking a slot for that event can delete an event
    Boolean deleteEvent(int eventId) throws CustomException;

    // get list of all events --> visible to everyone
    List<Map<String, Object>> getAllEvents();
    //get details of a specific event --> visible to everyone

    Event getEventById(int eventId) throws CustomException;

    // for filtering and sorting on all events by attribute -->visible to everyone

    <T> List<Event> getEventByAttribute(String attributeName, T attributeValue) throws CustomException;

    Map<String, Object> getEventDetails(int eventId);
}
