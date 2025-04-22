package br.com.fintech.models.entities.concrete;


import br.com.fintech.models.enums.TransactionStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionLog {
    private String transactionId;
    private TransactionStatus status;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public TransactionLog(String transactionId, TransactionStatus status, BigDecimal amount) {
        this.transactionId = transactionId;
        this.status = status;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "TransactionLog{" +
                "transactionId='" + transactionId + '\'' +
                ", status=" + status +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
