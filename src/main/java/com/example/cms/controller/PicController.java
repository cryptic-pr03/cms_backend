package com.example.cms.controller;


import org.springframework.web.bind.annotation.*;
import com.example.cms.Models.Pic;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.PicDAO;
import com.example.cms.dao.PicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pic")
public class PicController {
    PicDAO picDAO;
    public PicController(PicDAO picDAO){this.picDAO = picDAO;}

    @GetMapping("/pic/{venueId}")
    public List<Pic> getPicsByVenueId(@PathVariable("venueId") int venueId) throws CustomException {
        try{
            List<Pic> pics= picDAO.getPicsByVenueId(venueId);
            return pics;
        }catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{venuePicUrl}/{venueId}")
    public Boolean deletePic(@PathVariable("venuePicUrl") String venuePicUrl, @PathVariable("venueId") int venueId) throws CustomException{
        try{
            return picDAO.deletePic(venuePicUrl, venueId);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping
    public Pic addPic(@RequestBody Pic newPic) throws CustomException {
        try {
            Pic addedpic =picDAO.addPic(newPic);
            return addedpic;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

}
