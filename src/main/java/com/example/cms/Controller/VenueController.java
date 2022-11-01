package com.example.cms.Controller;

import com.example.cms.Models.Venue;
import com.example.cms.dao.VenueDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venue")
@CrossOrigin(origins = "http://localhost:3000")
public class VenueController {

    private final VenueDAO venueRepo;

    public VenueController(VenueDAO venueRepo) {
        this.venueRepo = venueRepo;
    }

    @PostMapping
    public ResponseEntity addvenue(@RequestBody Venue venue){
        try {
            Venue addedVenue = venueRepo.addVenue(venue);
            return ResponseEntity.ok(addedVenue);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateVenue(@RequestBody Venue venue){
        try{
            Venue updatedVenue = venueRepo.updateVenue(venue.getVenueId(), venue);
            return ResponseEntity.ok(updatedVenue);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{venueId}")
    public ResponseEntity deleteVenue(@PathVariable int venueId){
        try{
            if (venueRepo.deleteVenue(venueId)){
                return ResponseEntity.ok("Venue deleted successfully !!");
            }
            else{
                return ResponseEntity.badRequest().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllvenues(){
        return ResponseEntity.ok(venueRepo.getAllVenues());
    }

    @GetMapping("/id/{venueId}")
    public ResponseEntity venueById(@PathVariable int venueId){
        try {
            return ResponseEntity.ok(venueRepo.getVenueById(venueId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("attribute/{attributeName}/{attributeValue}")
    public ResponseEntity getvenueByAttribute(@PathVariable String attributeName, @PathVariable Object attributeValue){
        try {
            return ResponseEntity.ok(venueRepo.getVenueByAttribute(attributeName, attributeValue));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
