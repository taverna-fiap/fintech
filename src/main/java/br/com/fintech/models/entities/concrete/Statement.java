package br.com.fintech.models.entities.concrete;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Statement {
    private String statementId;
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status;
    private String account;

    public Statement(String transactionId, BigDecimal amount, LocalDateTime timestamp, String status, String account) {
        this.statementId = UUID.randomUUID().toString();
        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
        this.account = account;
    }


    @Override
    public String toString() {
        return "Statement{" +
               "statementId='" + statementId + '\'' +
               ", transactionId='" + transactionId + '\'' +
               ", amount=" + amount +
               ", timestamp=" + timestamp +
               ", status='" + status + '\'' +
               ", account='" + account + '\'' +
               '}';
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}