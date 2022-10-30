package com.example.cms.controller;

import com.example.cms.Models.Sponsor;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.SponsorDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sponsor")
public class SponsorController {
    SponsorDAO sponsorDAO;
    public SponsorController(SponsorDAO sponsorDAO){this.sponsorDAO = sponsorDAO;}

    @GetMapping("/sponsor/{eventId}")
    public List<Sponsor> getSponsorsByEventId(@PathVariable("eventId") int eventId) throws CustomException {
        try{
            List<Sponsor> sponsors= sponsorDAO.getSponsorsByEventId(eventId);
            return sponsors;
        }catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }



    @PostMapping
    public Sponsor addSponsor(@RequestBody Sponsor newSponsor) throws CustomException {
        try {
            Sponsor addedSponsor =sponsorDAO.addSponsor(newSponsor);
            return addedSponsor;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{eventId}/{sponsorName}")
    public Boolean deleteSponsor(@PathVariable("eventId") int eventId, @PathVariable("sponsorName") String sponsorName) throws CustomException{
        try{
            return sponsorDAO.deleteSponsor(eventId, sponsorName);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }




}
