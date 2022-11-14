package com.example.cms.Controllers;

import com.example.cms.Models.WorksFor;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.WorksForDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worksFor")
@CrossOrigin(origins = {"http://localhost:3000","http://cms-frontend-tau.vercel.app/"})
public class WorksForController {
    private final WorksForDAO worksForRepo;

    public WorksForController(WorksForDAO worksForRepo) {
        this.worksForRepo = worksForRepo;
    }

    @PostMapping("/")
    public WorksFor addWorksFor(@RequestBody WorksFor worksFor) throws CustomException {
        try {
            return worksForRepo.addWorksFor(worksFor);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/deleteWorksFor/{staffId}/{eventId}")
    public boolean deleteWorksFor(@PathVariable int staffId, @PathVariable int eventId) throws CustomException {
        try {
            return worksForRepo.deleteWorksFor(staffId, eventId);
        } catch (Exception e) {
            throw new CustomException("Could not delete works for with staffId: " + staffId + " and eventId: " + eventId);
        }
    }

    @GetMapping("/getAllWorkersByEvent/{eventId}")
    public List<Integer> getAllWorkersByEvent(@PathVariable int eventId) throws CustomException {
        try {
            return worksForRepo.getAllWorkersByEvent(eventId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getEventsByStaffId/{staffId}")
    public List<Integer> getEventsByStaffId(@PathVariable int staffId) throws CustomException {
        try {
            return worksForRepo.getEventsByStaffId(staffId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/assign/{eventId}/{groupNo}")
    @PreAuthorize("hasAnyAuthority('VENUE_MANAGER', 'ADMIN')")
    public ResponseEntity assignWork(@PathVariable int eventId, @PathVariable int groupNo){
        try{
            worksForRepo.assignWork(eventId, groupNo);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addAllStaffGno")
    public Integer addWorksForAllStaffGno(@RequestBody Map<String, Integer> staff) throws CustomException {
      try {
        System.out.println(staff);
        return worksForRepo.addWorksForAllStaffGno(staff);
      } catch (Exception e) {
        throw new CustomException(e.getMessage());
      }
    }
}
