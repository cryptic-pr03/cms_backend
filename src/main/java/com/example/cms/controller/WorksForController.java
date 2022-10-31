package com.example.cms.controller;

import com.example.cms.Models.WorksFor;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.WorksForDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worksFor")
public class WorksForController {
    private final WorksForDAO worksForDAO;

    public WorksForController(WorksForDAO worksForDAO) {
        this.worksForDAO = worksForDAO;
    }

    @PostMapping()
    public WorksFor addWorksFor(@RequestBody WorksFor worksFor) throws CustomException {
        try {
            return worksForDAO.addWorksFor(worksFor);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/deleteWorksFor/{staffId}/{eventId}")
    public boolean deleteWorksFor(@PathVariable int staffId, @PathVariable int eventId) throws CustomException {
        try {
            return worksForDAO.deleteWorksFor(staffId, eventId);
        } catch (Exception e) {
            throw new CustomException("Could not delete works for with staffId: " + staffId + " and eventId: " + eventId);
        }
    }

    @GetMapping("/getAllWorkersByEvent/{eventId}")
    public List<Integer> getAllWorkersByEvent(@PathVariable int eventId) throws CustomException {
        try {
            return worksForDAO.getAllWorkersByEvent(eventId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getEventsByStaffId/{staffId}")
    public List<Integer> getEventsByStaffId(@PathVariable int staffId) throws CustomException {
        try {
            return worksForDAO.getEventsByStaffId(staffId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
