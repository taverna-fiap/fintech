package Models;

public class Account {

    private String accountId;
    private String bank;
    private String agency;
    private String account;
    private String balance;

    public Account(String accountId, String bank, String agency, String account, String balance) {

        this.accountId = accountId;
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAgency() {
        return agency;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }


}
