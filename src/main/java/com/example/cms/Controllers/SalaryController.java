package com.example.cms.Controllers;

import com.example.cms.Models.Salary;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.SalaryDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    SalaryDAO salaryDAO;

    public SalaryController(SalaryDAO salaryDAO) {
        this.salaryDAO = salaryDAO;
    }

    @GetMapping("/salary/{staffId}")
    public List<Salary> getAllSalariesOfStaff(@PathVariable("staffId") int staffId) throws CustomException {
        try {
            List<Salary> salary = salaryDAO.getAllSalariesOfStaff(staffId);
            return salary;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    @PostMapping
    public Salary addSalary(@RequestBody Salary newSalary) throws CustomException {
        try {
            Salary addedsalary = salaryDAO.addSalary(newSalary);
            return addedsalary;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping
    public Salary changePaidStatus(@RequestBody Salary salary) throws CustomException {
        try {
            Salary updatedSalary = salaryDAO.changePaidStatus(salary.getStaffId(), salary.getTimeOfPayment(), salary);
            return updatedSalary;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


}
