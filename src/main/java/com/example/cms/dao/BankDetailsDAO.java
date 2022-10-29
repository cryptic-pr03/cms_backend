package com.example.cms.dao;

import com.example.cms.Models.BankDetails;

import java.util.List;

public interface BankDetailsDAO {

    BankDetails addBankDetails(BankDetails newBankDetails) throws CustomException;

    Boolean deleteBankDetails(int accountNo) throws CustomException;

     <T> List<BankDetails> getBankDetailsByAttribute(String attributeName, T attributeValue);

    List<BankDetails> getBankDetailsByUserId(int userId) throws CustomException;

}
