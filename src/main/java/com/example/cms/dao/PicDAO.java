package com.example.cms.dao;

import com.example.cms.Models.Pic;

import java.util.List;

public interface PicDAO {

    Pic addPic(Pic newPic) throws CustomException;
    Boolean deletePic(String venuePicUrl, int venueId) throws CustomException;
    List<Pic> getPicsByVenueId(int venueId) throws CustomException;

}
