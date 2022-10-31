package com.example.cms.Controllers;

import com.example.cms.Models.Seat;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.SeatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatDAO seatDAO;

    @GetMapping("/unique/{venueId}/{seatId}")
    public Seat getSeat(@PathVariable int venueId, @PathVariable int seatId) throws CustomException {
        try {
            Seat seat = seatDAO.getSeat(seatId, venueId);
            return seat;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping
    public Seat addSeat(@RequestBody Seat seat) throws CustomException {
        try {
            Seat addedseat = seatDAO.addSeat(seat);
            return addedseat;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping
    public Seat updateSeat(@RequestBody Seat seat) throws CustomException {
        try {
            Seat updatedSeat = seatDAO.updateSeat(seat.getSeatId(), seat.getVenueId(), seat);
            return updatedSeat;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{venueId}/{seatId}")
    public Boolean deleteSeat(@PathVariable int venueId, @PathVariable int seatId) throws CustomException {
        try {
            return seatDAO.deleteSeat(seatId, venueId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/seatByVenue/{venueId}")
    public List<Seat> getSeatByVenueId(@PathVariable int venueId) throws CustomException {
        try {
            List<Seat> ls = seatDAO.getSeatByVenueId(venueId);
            return ls;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/seatByVenueByType/{venueId}/{seatType}")
    public List<Seat> getSeatAtVenueByType(@PathVariable int venueId, @PathVariable String seatType) throws CustomException {
        try {
            List<Seat> ls = seatDAO.getSeatAtVenueByType(venueId, seatType);
            return ls;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
