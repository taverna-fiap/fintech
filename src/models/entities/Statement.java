package models.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Statement {
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status;
    private String account;

    public Statement(String transactionId, BigDecimal amount, LocalDateTime timestamp, String status, String account) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Financial Statement: \n" +
                "Transaction ID: " + transactionId + "\n" +
                "Amount: " + amount + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Status: " + status + "\n" +
                "Account: " + account;
    }
}