package br.com.fintech.dao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.CheckingAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class CheckingAccountDao {
    private final Connection connection;

    public CheckingAccountDao() {
        try {
            connection = DatabaseFactory.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCheckingAccount(CheckingAccount account) throws SQLException {
        connection.setAutoCommit(false); // Inicia transação

        try {
            String accountSql = "INSERT INTO account (account_id, user_id, bank, agency, account_number, balance) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement accountStmt = connection.prepareStatement(accountSql);
            accountStmt.setInt(1, account.getAccountId());
            accountStmt.setString(2, account.getUserId());
            accountStmt.setString(3, account.getBank());
            accountStmt.setString(4, account.getAgency());
            accountStmt.setString(5, account.getAccount());
            accountStmt.setBigDecimal(6, account.getBalance());
            accountStmt.executeUpdate();

            String checkingSql = "INSERT INTO checking_account (account_id) VALUES (?)";
            PreparedStatement checkingStmt = connection.prepareStatement(checkingSql);
            checkingStmt.setInt(1, account.getAccountId());
            checkingStmt.executeUpdate();

            connection.commit(); // Confirma transação

        } catch (SQLException e) {
            connection.rollback(); // Reverte se der ruim
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restaura
        }
    }

    public List<CheckingAccount> getAllCheckingAccounts() throws SQLException {
        List<CheckingAccount> accounts = new ArrayList<>();

        String sql = """
            SELECT 
                a.account_id, a.user_id, a.bank, a.agency, a.account_number, a.balance
            FROM account a
            JOIN checking_account c ON a.account_id = c.account_id
        """;

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int accountId = rs.getInt("account_id");
            String userId = rs.getString("user_id");
            String bank = rs.getString("bank");
            String agency = rs.getString("agency");
            String accountNumber = rs.getString("account_number");
            BigDecimal balance = rs.getBigDecimal("balance");

            CheckingAccount account = new CheckingAccount(accountId, userId, bank, agency, accountNumber, balance);
            accounts.add(account);
        }

        return accounts;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
