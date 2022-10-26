package com.example.cms.controller;

import com.example.cms.Models.BankDetails;
import com.example.cms.dao.BankDetailsDAO;
import com.example.cms.dao.CustomException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankDetails")
public class BankDetailsController {
    BankDetailsDAO bankDetailsDAO;

    public BankDetailsController(BankDetailsDAO bankDetailsDAO){this.bankDetailsDAO=bankDetailsDAO;}

    @GetMapping("/bankDetails/{userId}")
    public List<BankDetails> getBankDetailsByUserId(@PathVariable("userId") int userId) throws CustomException {
        try{
            List<BankDetails> bankDetailsList= bankDetailsDAO.getBankDetailsByUserId(userId);
            return bankDetailsList;
        }catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/bankDetails/{attributeName}/{attributeValue}")
    public <T> List<BankDetails> getBankDetailsByAttribute(@PathVariable("attributeName") String attributeName,@PathVariable("attributeValue") T attributeValue) throws CustomException{
        try{
            List<BankDetails> bankDetailsList= bankDetailsDAO.getBankDetailsByAttribute(attributeName, attributeValue);
            return bankDetailsList;
        }catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{accountNo}")
    public Boolean deleteBankDetails(@PathVariable("accountNo") int accountNo) throws CustomException{
        try{
            return bankDetailsDAO.deleteBankDetails(accountNo);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping
    public BankDetails addBankDetails(@RequestBody BankDetails bankDetails) throws CustomException {
        try {
            BankDetails addedbankDetails =bankDetailsDAO.addBankDetails(bankDetails);
            return addedbankDetails;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }






}
