package models.entities;

import models.enums.TransactionStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionStatus status;
    private Account account;
    private Category category; // Categoria da transação

    public Transaction(BigDecimal amount, Account account, Category category) {
        this.transactionId = UUID.randomUUID().toString();
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        this.account = account;
        this.category = category;
    }

    public void process() {
        try {
            account.debit(amount, transactionId);
            this.status = TransactionStatus.COMPLETED;
        } catch (IllegalArgumentException e) {
            this.status = TransactionStatus.FAILED;
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + transactionId + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", account=" + account +
                ", category=" + (category != null ? category.getName() : "Nenhuma") +
                '}';
    }
}
