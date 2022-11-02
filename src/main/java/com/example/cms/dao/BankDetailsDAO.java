package com.example.cms.dao;

import com.example.cms.Models.BankDetails;

import java.util.List;

public interface BankDetailsDAO {

    BankDetails addBankDetails(BankDetails newBankDetails) throws CustomException;

    Boolean deleteBankDetails(int accountNo, int userId) throws CustomException;

    List<BankDetails> getBankDetailsByUserId(int userId) throws CustomException;

}
