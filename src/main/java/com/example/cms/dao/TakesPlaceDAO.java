package com.example.cms.dao;

import com.example.cms.Models.Event;
import com.example.cms.Models.TakesPlace;

import java.util.List;

public interface TakesPlaceDAO {
    TakesPlace addTakesPlace(TakesPlace newTakesPlace) throws CustomException;
    List<Event> getEventsHeldInVenue(int venueId) throws CustomException;
    <T> TakesPlace getTakesPlaceByAttribute(String attributeName, T attributeValue) throws CustomException;

}
