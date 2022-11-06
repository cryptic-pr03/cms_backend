package com.example.cms.Controllers;

import com.example.cms.Models.Seat;
import com.example.cms.Models.Venue;
import com.example.cms.dao.SeatDAO;
import com.example.cms.dao.StaffDAO;
import com.example.cms.dao.VenueDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venue")
@CrossOrigin(origins = "http://localhost:3000")
public class VenueController {

    private final VenueDAO venueRepo;
    private final StaffDAO staffRepo;

    private final SeatDAO seatRepo;

    public VenueController(VenueDAO venueRepo, StaffDAO staffRepo, SeatDAO seatRepo) {
        this.venueRepo = venueRepo;
        this.staffRepo = staffRepo;
        this.seatRepo = seatRepo;
    }

    @PostMapping
    public ResponseEntity addvenue(@RequestBody Venue venue) {
        try {
            System.out.println(venue);
            int silver = venue.getSilverSeats();
            int gold = venue.getGoldSeats();
            int platinum = venue.getPlatinumSeats();
            Venue addedVenue = venueRepo.addVenue(venue);
            int venueId = addedVenue.getVenueId();
            for (int i = 0; i < silver; ++i) {
                Seat seat = new Seat(i + 1, venueId, "Silver");
                seatRepo.addSeat(seat);
            }
            for (int i = 0; i < gold; ++i) {
                Seat seat = new Seat(silver + i + 1, venueId, "Gold");
                seatRepo.addSeat(seat);
            }
            for (int i = 0; i < platinum; ++i) {
                Seat seat = new Seat(silver + gold + i + 1, venueId, "Platinum");
                seatRepo.addSeat(seat);
            }
            return ResponseEntity.ok(addedVenue);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateVenue(@RequestBody Venue venue) {
        try {
            Venue updatedVenue = venueRepo.updateVenue(venue.getVenueId(), venue);
            return ResponseEntity.ok(updatedVenue);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity deleteVenue(@PathVariable int venueId) {
        try {
            if (venueRepo.deleteVenue(venueId)) {
                return ResponseEntity.ok("Venue deleted successfully !!");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllvenues() {
        return ResponseEntity.ok(venueRepo.getAllVenues());
    }

    @GetMapping("/id/{venueId}")
    public ResponseEntity venueById(@PathVariable int venueId) {
        try {
            return ResponseEntity.ok(venueRepo.getVenueById(venueId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/attribute/{attributeName}/{attributeValue}")
    public ResponseEntity getvenueByAttribute(@PathVariable String attributeName, @PathVariable Object attributeValue) {
        try {
            return ResponseEntity.ok(venueRepo.getVenueByAttribute(attributeName, attributeValue));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/staff/{venueId}")
    @PreAuthorize("hasAnyAuthority('VENUE_MANAGER', 'ADMIN')")
    public ResponseEntity getStaffByVenue(@PathVariable int venueId) {
        try {
            return ResponseEntity.ok(staffRepo.getStaffByVenue(venueId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
