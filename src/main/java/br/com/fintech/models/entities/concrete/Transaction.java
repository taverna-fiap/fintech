package br.com.fintech.models.entities.concrete;

import br.com.fintech.models.entities.abstracts.Account;
import br.com.fintech.services.auth.Login;
import br.com.fintech.models.enums.TransactionStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionStatus status;
    private Account account;
    private Statement statement;

    public Transaction() {

    }


    public Statement financialStatement(String transactionId, BigDecimal amount, LocalDateTime timestamp, Enum<TransactionStatus> status ,Account account) {
        return new Statement(transactionId, amount, timestamp, status.toString(), account.toString());
    }


    public Transaction(BigDecimal amount, Account account) {
        if (!Login.isUserLoggedIn()) {
            throw new IllegalStateException("Erro: Você precisa estar logado para fazer uma transação.");
        }

        this.transactionId = UUID.randomUUID().toString();
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        this.account = account;
        this.statement = financialStatement(this.transactionId, this.amount, this.timestamp, this.status, this.account);
    }

    public void process() {
        try {
            account.debit(amount, transactionId);
            this.status = TransactionStatus.COMPLETED;
            System.out.println("Transação concluída com sucesso.");
        } catch (IllegalArgumentException e) {
            this.status = TransactionStatus.FAILED;
            System.out.println("Erro ao processar transação: " + e.getMessage());
        }
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + transactionId + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", account=" + account +
                '}';
    }
}