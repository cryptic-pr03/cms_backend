package com.example.cms.Controllers;

import com.example.cms.Models.EventSeat;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.EventSeatDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventSeat")
// @PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://localhost:3000")
public class EventSeatController {

    private final EventSeatDAO eventSeatRepo;

    public EventSeatController(EventSeatDAO eventSeatRepo) {
        this.eventSeatRepo = eventSeatRepo;
    }

    @GetMapping("/{eventId}")
//    @PreAuthorize("isAuthenticated()")
    // @PreAuthorize("isAnonymous()")
    public ResponseEntity getEventSeatDetails(@PathVariable int eventId){
        try {
            return ResponseEntity.ok(eventSeatRepo.getEventSeatDetails(eventId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/seatDetail/{seatId}/{eventId}")
    public ResponseEntity getSeat(@PathVariable int seatId, @PathVariable int eventId){
        try {
            return ResponseEntity.ok(eventSeatRepo.getSeat(seatId, eventId));
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/update/{seatId}/{eventId}")
    public ResponseEntity updateSeat(@PathVariable int seatId, @PathVariable int eventId, @RequestBody EventSeat eventSeat){
        try {
            return ResponseEntity.ok(eventSeatRepo.updateSeat(seatId, eventId, eventSeat));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/book/{eventId}")
    public ResponseEntity bookSeats(@PathVariable int eventId, @RequestBody List<Integer> seatIds){
        try {
            return ResponseEntity.ok(eventSeatRepo.bookSeats(seatIds, eventId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/init/{eventId}")
    public ResponseEntity init(@PathVariable int eventId){
        try {
            eventSeatRepo.initEventSeats(eventId);
            return ResponseEntity.ok("Initialized successfully");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity deleteEventSeats(@PathVariable int eventId){
        try {
            eventSeatRepo.deleteEventSeats(eventId);
            return ResponseEntity.ok("Event deleted successfully !!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

