package com.example.cms.Controllers;

import com.example.cms.dao.CustomException;
import com.example.cms.dao.TakesPlaceDAO;
import com.example.cms.Models.Event;
import com.example.cms.Models.TakesPlace;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/takesPlace")
public class TakesPlaceController {
    private final TakesPlaceDAO takesPlaceDAO;

    public TakesPlaceController(TakesPlaceDAO takesPlaceDAO) {
        this.takesPlaceDAO = takesPlaceDAO;
    }

    @PostMapping()
    public TakesPlace addTakesPlace(@RequestBody TakesPlace takesPlace) throws CustomException {
        try {
            return takesPlaceDAO.addTakesPlace(takesPlace);
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getEventsHeldInVenue/{venueId}")
    public List<Event> getEventsHeldInVenue(@PathVariable int venueId) throws CustomException {
        try {
            return takesPlaceDAO.getEventsHeldInVenue(venueId);
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getTakesPlaceByAttribute/{attributeName}/{attributeValue}")
    public <T> List<TakesPlace> getTakesPlaceByAttribute(@PathVariable String attributeName, @PathVariable String attributeValue) throws CustomException {
        try {
            return takesPlaceDAO.getTakesPlaceByAttribute(attributeName, attributeValue);
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
