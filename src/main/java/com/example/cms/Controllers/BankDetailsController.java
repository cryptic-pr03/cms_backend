package com.example.cms.Controllers;

import com.example.cms.Models.BankDetails;
import com.example.cms.dao.BankDetailsDAO;
import com.example.cms.dao.CustomException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankDetails")
public class BankDetailsController {
    BankDetailsDAO bankDetailsDAO;

    public BankDetailsController(BankDetailsDAO bankDetailsDAO) {
        this.bankDetailsDAO = bankDetailsDAO;
    }

    @GetMapping("/bankDetails/{userId}")
    public List<BankDetails> getBankDetailsByUserId(@PathVariable("userId") int userId) throws CustomException {
        try {
            List<BankDetails> bankDetailsList = bankDetailsDAO.getBankDetailsByUserId(userId);
            return bankDetailsList;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }



    @DeleteMapping("/delete/{accountNo}/{userId}")
    public Boolean deleteBankDetails(@PathVariable("accountNo") int accountNo, @PathVariable("userId") int userId) throws CustomException {
        try {
            return bankDetailsDAO.deleteBankDetails(accountNo,userId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping
    public BankDetails addBankDetails(@RequestBody BankDetails bankDetails) throws CustomException {
        try {
            BankDetails addedbankDetails = bankDetailsDAO.addBankDetails(bankDetails);
            return addedbankDetails;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


}
