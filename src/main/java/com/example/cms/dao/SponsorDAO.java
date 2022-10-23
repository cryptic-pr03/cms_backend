package com.example.cms.dao;

import com.example.cms.Models.Sponsor;

import java.util.List;

public interface SponsorDAO {
    List<Sponsor> getSponsorsByEventId(int eventId) ;
    Sponsor addSponsor(Sponsor newSponsor) throws CustomException;
    Boolean deleteSponsor(int eventId, String sponsorName) throws CustomException;
}
