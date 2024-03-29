package com.example.cms.Controllers;

import com.example.cms.Models.Event;
import com.example.cms.dao.EventDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final EventDAO eventRepo;

    public EventController(EventDAO eventRepo) {
        this.eventRepo = eventRepo;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('VENUE_MANAGER', 'ADMIN')")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
      System.out.println("addevent");
      System.out.println(event);
    // public ResponseEntity addEvent(@RequestBody Event event) {

        try {
            Event addedEvent = eventRepo.addEvent(event);
            return ResponseEntity.ok(addedEvent);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateEvent(@RequestBody Event event) {
        try {
            Event updatedEvent = eventRepo.updateEvent(event.getEventId(), event);
            return ResponseEntity.ok(updatedEvent);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity deleteEvent(@PathVariable int eventId) {
        try {
            if (eventRepo.deleteEvent(eventId)) {
                return ResponseEntity.ok("Event deleted successfully !!");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllEvents() {
        try {
            System.out.println("GETALLEVENTS");
            return ResponseEntity.ok(eventRepo.getAllEvents());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/id/{eventId}")
    public ResponseEntity eventById(@PathVariable int eventId) {
        try {
            return ResponseEntity.ok(eventRepo.getEventById(eventId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/eventDetails/{eventId}")
    public ResponseEntity eventDetails(@PathVariable int eventId) {
        try {
            System.out.println("getting event details");
            return ResponseEntity.ok(eventRepo.getEventDetails(eventId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("attribute/{attributeName}/{attributeValue}")
    public ResponseEntity getEventByAttribute(@PathVariable String attributeName, @PathVariable Object attributeValue) {
        try {
            return ResponseEntity.ok(eventRepo.getEventByAttribute(attributeName, attributeValue));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
