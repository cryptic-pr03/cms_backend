package com.example.cms.controller;

import com.example.cms.Models.Transaction;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.TransactionDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionDAO transactionDAO;

    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @PostMapping
    public Transaction addTrxn(@RequestBody Transaction transaction) throws CustomException {
        try {
            return transactionDAO.addTrxn(transaction);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getTrxnsByUserId/{userId}")
    public List<Transaction> getTrxnsByUserId(@PathVariable int userId) throws CustomException {
        try {
            return transactionDAO.getTrxnsByUserId(userId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getTrxnsByEventId/{eventId}")
    public List<Transaction> getTrxnsByEventId(@PathVariable int eventId) throws CustomException {
        try {
            return transactionDAO.getTrxnsByEventId(eventId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
