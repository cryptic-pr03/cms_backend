package com.example.cms.Controllers;

import com.example.cms.Models.Transaction;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.TransactionDAO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    private final TransactionDAO transactionDAO;

    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }


    @Value("${stripe.api.key}")
    private String secretKey;
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Map<String, Object> createPayment) throws StripeException {

        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", ((int) createPayment.get("amount")) * 100L);
        params.put("currency", "inr");
        params.put("payment_method_types", paymentMethodTypes);
        Map<String, String> transType = new HashMap<>();
        transType.put("Transaction Type", (String) createPayment.get("transactionType"));
        params.put("metadata", transType);

        PaymentIntent intent = PaymentIntent.create(params);

        return ResponseEntity.ok(intent.getClientSecret());
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
