package com.example.cms.dao;

import com.example.cms.Models.Slot;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SlotDAO {
    // at the beginning of a week, the Venue Manager will add the slot details for the next 7th day
    // we can do some default thing ,but it is not implemented yet
    //if he wants to change the slot details
    Slot addSlot(Slot newSlot) throws  CustomException;
    Slot updateSlot (Slot newSlot, int venueId, int slotId, Date slotDate) throws CustomException;
    Boolean deleteSlot(int venueId, int slotId, Date slotDate) throws CustomException;
    <T> List<Slot> getSlotByAttribute(String attributeName, T attributeValue) throws CustomException;
    List<Map<String, Object>> getAllSlots();
}
