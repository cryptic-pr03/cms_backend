package com.example.cms.Controllers;

import com.example.cms.Models.Slot;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.SlotDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/slot")
public class SlotController {
    @Autowired
    SlotDAO slotDAO;

    @GetMapping("/attribute/{attributeName}/{attributeValue}")
    public <T> List<Slot> getSlotByAttribute(@PathVariable String attributeName, @PathVariable T attributeValue) throws CustomException {
        try {
            List<Slot> ls = slotDAO.getSlotByAttribute(attributeName, attributeValue);
            return ls;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{venueId}/{slotId}/{slotDate}")
    public Boolean deleteSlot(@PathVariable int venueId, @PathVariable int slotId, @PathVariable Date slotDate) throws CustomException {
        try {
            return slotDAO.deleteSlot(venueId, slotId, slotDate);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping
    public Slot addSlot(@RequestBody Slot slot) throws CustomException {
        try {
            Slot addedSlot = slotDAO.addSlot(slot);
            return addedSlot;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping
    public Slot updateSlot(@RequestBody Slot slot) throws CustomException {
        try {
            Slot updatedSlot = slotDAO.updateSlot(slot, slot.getVenueId(), slot.getSlotId(), slot.getSlotDate());
            return updatedSlot;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity slots(){
        try {
            return ResponseEntity.ok(slotDAO.getAllSlots());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
