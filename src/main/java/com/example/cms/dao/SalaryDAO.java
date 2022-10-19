package com.example.cms.dao;

import com.example.cms.Models.Salary;

import java.util.List;

//DONT KNOW ITS USAGE -- >IRREELEVENT LAGRHA HAI
public interface SalaryDAO {
    Salary addSalary(Salary newSalary) throws CustomException;
    Salary changePaidStatus(int staffId, String timeOfPayment) throws CustomException;

    List<Salary> getAllSalariesOfStaff(int staffId);
}
