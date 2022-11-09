package com.example.cms.dao;

import com.example.cms.Models.Transaction;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface TransactionDAO {
  Transaction addTrxn(Transaction newTransaction) throws CustomException, ParseException;

  List<Transaction> getTrxnsByUserId(int userId);

  List<Transaction> getTrxnsByEventId(int eventId);

  void addEventIdForTrxn(String transactionId, int eventId);

  public List<Map<String, Object>> getAll(int userId);
}