package com.example.cms.Controllers;

import com.example.cms.Models.SeatBook;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.SeatBookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seatBook")
public class SeatBookController {

    @Autowired
    SeatBookDAO seatBookDAO;

    @GetMapping("/transactionId/{eventId}/{seatId}")
    public int getTrxnId(@PathVariable int eventId, @PathVariable int seatId) throws CustomException {
        try {
            int transactionId = seatBookDAO.getTrxnId(eventId, seatId);
            return transactionId;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping
    public SeatBook addSeatBook(@RequestBody SeatBook seatBook) throws CustomException {
        try {
            SeatBook addedSeatBook = seatBookDAO.addSeatBook(seatBook);
            return addedSeatBook;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
