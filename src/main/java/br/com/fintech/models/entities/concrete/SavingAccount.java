package br.com.fintech.models.entities.concrete;

import br.com.fintech.models.entities.abstracts.Account;
import br.com.fintech.models.enums.TransactionStatus;

import java.math.BigDecimal;

public class SavingAccount extends Account {
    private BigDecimal tax;


    public SavingAccount(int accountId,String userId, String bank, String agency, String account, BigDecimal balance, BigDecimal tax ) {
        super(accountId, userId,bank, agency, account, balance);
        this.tax = tax;
    }

    @Override
    public void debit(BigDecimal amount, String transactionId) {
        if(hasSufficientBalance(amount)){
            this.balance = this.balance.subtract(amount);
            addTransactionLog(transactionId, TransactionStatus.COMPLETED, amount);
        } else {
            addTransactionLog(transactionId, TransactionStatus.FAILED, amount);
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public void applyIncome() {
        BigDecimal income = balance.multiply(tax);
        this.balance = this.balance.add(income);
        System.out.println("Income after applying income" + income);
    }

    public BigDecimal getTax() {
        return tax;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
               "tax=" + tax +
               ", accountId=" + accountId +
               ", userId='" + userId + '\'' +
               ", bank='" + bank + '\'' +
               ", agency='" + agency + '\'' +
               ", account='" + account + '\'' +
               ", balance=" + balance +
               ", transactions=" + transactions +
               ", card=" + card +
               '}';
    }
}
