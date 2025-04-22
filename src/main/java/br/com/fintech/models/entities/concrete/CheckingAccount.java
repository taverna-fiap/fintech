package br.com.fintech.models.entities.concrete;

import br.com.fintech.models.entities.abstracts.Account;
import br.com.fintech.models.enums.TransactionStatus;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
    private BigDecimal specialCredit;
    public CheckingAccount(int accountId,String userId,String bank, String agency, String account, BigDecimal balance, BigDecimal specialCredit) {
        super(accountId,userId ,bank, agency, account, balance);
        this.specialCredit = (specialCredit != null) ? specialCredit : BigDecimal.ZERO;
    }
    public CheckingAccount(int accountId,String userId, String bank, String agency, String account, BigDecimal balance) {
        super(accountId,userId ,bank, agency, account, balance);
        this.specialCredit = BigDecimal.ZERO;
    }

    @Override
    public void debit(BigDecimal amount, String transactionId){
        BigDecimal availableLimit = balance.add(specialCredit);
        if(availableLimit.compareTo(amount) < 0){
            this.balance = balance.subtract(availableLimit);
            addTransactionLog(transactionId, TransactionStatus.COMPLETED, amount);
        }else {
            addTransactionLog(transactionId, TransactionStatus.FAILED, amount);
            throw new IllegalArgumentException("insufficient balance");
        }
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
               "specialCredit=" + specialCredit +
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
