package models.entities;

import models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private String bank;
    private String agency;
    private String account;
    private BigDecimal balance;
    private List<TransactionLog> log;

    public Account(String accountId, String bank, String agency, String account, BigDecimal balance) {
        this.accountId = accountId;
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.balance = balance;
        this.log = new ArrayList<>();
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    public void debit(BigDecimal amount, String transactionId) {
        if (hasSufficientBalance(amount)) {
            this.balance = this.balance.subtract(amount);
            addTransactionLog(transactionId, TransactionStatus.COMPLETED, amount);
        } else {
            addTransactionLog(transactionId, TransactionStatus.FAILED, amount);
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
    }

    public void credit(BigDecimal amount, String transactionId) {
        this.balance = this.balance.add(amount);
        addTransactionLog(transactionId, TransactionStatus.COMPLETED, amount);
    }

    private void addTransactionLog(String transactionId, TransactionStatus status, BigDecimal amount) {
        TransactionLog newLog = new TransactionLog(transactionId, status, amount);
        log.add(newLog);
        System.out.println("Log de transação criado: " + newLog);
    }

    public List<TransactionLog> getTransactionLogs() {
        return log;
    }

    public String toString() {
        return "Conta: " + this.account +
                "\nAgência: " + this.agency +
                "\nBanco: " + this.bank +
                "\nSaldo: R$ " + this.balance;
    }
}
