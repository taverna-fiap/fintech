package br.com.fintech.models.entities.abstracts;
import br.com.fintech.models.entities.concrete.CardInfo;
import br.com.fintech.models.entities.concrete.TransactionLog;
import br.com.fintech.models.entities.concrete.User;
import br.com.fintech.models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public abstract class Account {
    protected int accountId;
    protected String userId;
    protected String bank;
    protected String agency;
    protected String account;
    protected BigDecimal balance; // here we chose int for more precision, futhermore, creating a method to convert to float.
    protected List<TransactionLog> transactions;
    protected List<CardInfo> card;

    public Account(int accountId, String userId ,String bank, String agency, String account, BigDecimal balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.card = new ArrayList<>();
    }

    public void addCard(CardInfo card) {
        this.card.add(new CardInfo(card.getCardId(), card.getCardName(), card.getCardNumber(), card.getCvv(), card.getExpiration(), card.getExpiration()));
    } // alterar a forma de adição posteriormente


//    public float convertBalance() {
//        float convertedBalance;
//        convertedBalance = (float) (balance/100);
//        return convertedBalance;
//    }

    public boolean hasSufficientBalance(BigDecimal sufficientBalance) {
        return balance.compareTo(sufficientBalance) >= 0;
    }

    public abstract void debit(BigDecimal amount, String transactionId);

    public void credit(BigDecimal amount, String transactionId) {
        this.balance = this.balance.add(amount);
        addTransactionLog(transactionId, TransactionStatus.COMPLETED, amount);
    }

    protected void addTransactionLog(String transactionId, TransactionStatus status, BigDecimal amount) {
        TransactionLog transactionLog = new TransactionLog(transactionId, status, amount);
        transactions.add(transactionLog);
        System.out.println("Log de transação criado" + transactionLog);
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setTransactions(List<TransactionLog> transactions) {
        this.transactions = transactions;
    }

    public List<CardInfo> getCard() {
        return card;
    }

    public void setCard(List<CardInfo> card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Account{" +
               "accountId=" + accountId +
               ", userId='" + userId + '\'' +
               ", bank='" + bank + '\'' +
               ", agency='" + agency + '\'' +
               ", account='" + account + '\'' +
               ", balance=" + balance +
               ", transactions=" + transactions +
               ", card=" + card +
               '}';
    }

    public List<TransactionLog> getTransactions() {
        return transactions;
    }
}

