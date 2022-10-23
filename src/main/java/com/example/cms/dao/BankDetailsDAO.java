package com.example.cms.dao;

import com.example.cms.Models.BankDetails;

import java.util.List;

public interface BankDetailsDAO {

    BankDetails addBankDetails(BankDetails newBankDetails) throws CustomException;

    Boolean deleteBankDetails(BankDetails bankDetails) throws CustomException;

    BankDetails updateBankDetails(BankDetails updatedBankDetails , int userId) throws CustomException;

    List<BankDetails> getBankDetailsByAttribute(String attributeName, String attributeValue);

    List<BankDetails> getBankDetailsByUserId(int userId) throws CustomException;

}
