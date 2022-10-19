package com.example.cms.dao;

import com.example.cms.Models.Transaction;

import java.util.List;

public interface TransactionDAO {
    Transaction addTrxn(Transaction newTransaction) throws CustomException;
    List<Transaction> getTrxnsByUserId(int userId);
    List<Transaction> getTrxnsByEventId(int eventId);
}
