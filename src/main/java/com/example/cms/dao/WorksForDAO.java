package com.example.cms.dao;

import com.example.cms.Models.WorksFor;

import java.util.List;
import java.util.Map;

public interface WorksForDAO {
    WorksFor addWorksFor( WorksFor newWorksFor) throws  CustomException;
    Boolean deleteWorksFor(int userId, int EventId) throws CustomException;
    List<Integer> getAllWorkersByEvent (int eventId) ;
    List<Integer> getEventsByStaffId(int staffId);

    void assignWork(int eventId, int groupNo);
}
