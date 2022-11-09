package com.example.cms.Controllers;

import com.example.cms.Models.Event;
import com.example.cms.Models.SeatBook;
import com.example.cms.Models.Transaction;
import com.example.cms.dao.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

  private final TransactionDAO transactionDAO;
  private final JdbcTemplate jdbcTemplate;
  private final SeatBookDAO seatBookRepo;
  private final EventDAO eventRepo;
  private final UserDAO userDAO;
  private final EventSeatDAO eventSeatRepo;

  public TransactionController(TransactionDAO transactionDAO, JdbcTemplate jdbcTemplate, EventDAO eventRepo,
      SeatBookDAO seatBookRepo,
      UserDAO userDAO, EventSeatDAO eventSeatRepo) {
    this.transactionDAO = transactionDAO;
    this.jdbcTemplate = jdbcTemplate;
    this.eventRepo = eventRepo;
    this.userDAO = userDAO;
    this.seatBookRepo = seatBookRepo;
    this.eventSeatRepo = eventSeatRepo;
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

  @PostMapping("/payForEvent")
  public ResponseEntity<?> payForEvent(@RequestBody Map<String, Object> req) throws CustomException, ParseException {
    System.out.println(req);
    int amount = 0;
    List<Map<String, Object>> slots = (List<Map<String, Object>>) req.get("slots");

    String sql = "UPDATE Slot SET isRented = TRUE WHERE slotId = ? AND venueId = ? AND slotDate = ?";
    for (Map<String, Object> slot : slots) {
      amount += (int) slot.get("price");
      jdbcTemplate.update(sql, (int) slot.get("slotId"), (int) slot.get("venueId"), slot.get("slotDate"));
    }
    // UserDetails userPrincipal = (UserDetails)
    // SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    // System.out.println(userPrincipal.getUsername());
    int userId = userDAO.getUserByEmailId((String) req.get("email")).getUserID();
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setUserId(userId);

    transaction.setType("ARTIST_MANAGER");
    transaction = transactionDAO.addTrxn(transaction);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date dtst = sdf.parse((String) req.get("eventDate"));
    java.sql.Date eDate = new java.sql.Date(dtst.getTime());

    SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
    java.util.Date idk = sdf2.parse((String) req.get("startTime"));
    java.sql.Time eStartTime = new java.sql.Time(idk.getTime());

    idk = sdf2.parse((String) req.get("endTime"));
    java.sql.Time eEndTime = new java.sql.Time(idk.getTime());

    Event event = new Event();
    event.setName((String) req.get("name"));
    event.setAgeLimit(Integer.parseInt((String) req.get("ageLimit")));
    event.setLogoUrl((String) req.get("logoUrl"));
    event.setDescription((String) req.get("description"));
    event.setEventDate(eDate);
    event.setStartTime(eStartTime);
    event.setEndTime(eEndTime);

    event = eventRepo.addEvent(event);
    // System.out.println("here" + event.getEventId());
    transactionDAO.addEventIdForTrxn(transaction.getTransactionId(), event.getEventId());

    String sql2 = "INSERT INTO TakesPlace(venueId, slotId, eventId) VALUES(?, ?, ?)";
    for (Map<String, Object> slot : slots) {
      jdbcTemplate.update(sql2, (int) slot.get("venueId"), (int) slot.get("slotId"), event.getEventId());
    }

    eventSeatRepo.initEventSeats(event.getEventId());
    return /* Event with eventId = eventId */ ResponseEntity.ok("Everything's good !!");
  }

  @PostMapping("/payForSeat")
  public ResponseEntity payForSeat(@RequestBody List<Map<String, Object>> seats)
      throws CustomException, ParseException {
        System.out.print("event id seat book : ");
        System.out.println((Integer) seats.get(0).get("eventId"));
    int amount = 0;
    String sql = "UPDATE EventSeat SET isBooked = TRUE WHERE seatId = ? AND eventId = ?";
    for (Map<String, Object> seat : seats) {
      amount += (int) seat.get("price");
      jdbcTemplate.update(sql, (int) seat.get("seatId"), (int) seat.get("eventId"));
    }
    UserDetails userPrincipal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println(userPrincipal.getUsername());
    int userId = userDAO.getUserByEmailId(userPrincipal.getUsername()).getUserID();
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setUserId(userId);
    // transaction.setEventId((Integer)seats.get(0).get("eventId"));
    transaction.setType("AUDIENCE");

    transaction = transactionDAO.addTrxn(transaction);
    // String sql2 = "UPDATE SeatBook SET transactionId = ? WHERE seatId = ? AND
    // eventId = ?";
    transactionDAO.addEventIdForTrxn(transaction.getTransactionId(), (Integer) seats.get(0).get("eventId"));
    for (Map<String, Object> seat : seats) {
      SeatBook newSeatBook = new SeatBook((int) seat.get("eventId"), (int) seat.get("seatId"),
          transaction.getTransactionId());

      seatBookRepo.addSeatBook(newSeatBook);
      // jdbcTemplate.update(sql2, transaction.getTransactionId(), (int)
      // seat.get("seatId"), (int) seat.get("eventId"));
    }
    return /* Event with eventId = eventId */ ResponseEntity.ok().build();
  }

  @PostMapping
  public Transaction addTrxn(@RequestBody Transaction transaction) throws CustomException {
    System.out.println(transaction);
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

  @GetMapping("/getAll/{userId}")
  public List<Map<String, Object>> getAll(@PathVariable int userId) throws CustomException {
    try {
      return transactionDAO.getAll(userId);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
  }
}