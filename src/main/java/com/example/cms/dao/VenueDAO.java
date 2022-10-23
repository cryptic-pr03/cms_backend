package com.example.cms.dao;

import com.example.cms.Models.Venue;

import java.util.List;

public interface VenueDAO {

    //venue Manager can add a venue
    Venue addVenue(Venue newVenue) throws CustomException;
    // venue manager can update a venue
    Venue updateVenue(int venueId, Venue updatedVenue) throws CustomException;
    // just change isFunctional to false
    Venue deleteVenue(int venueId) throws CustomException;

    //admin can get list of all venues
    List<Venue> getAllVenues();

    Venue getVenueById(int venueId) throws CustomException;
    List<Venue> getVenueByAttribute(String attributeName, String attributeValue) throws CustomException;
}
