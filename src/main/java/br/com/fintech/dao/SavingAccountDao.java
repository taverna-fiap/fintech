package br.com.fintech.dao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.SavingAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class SavingAccountDao {
    private final Connection connection;

    public SavingAccountDao() {
        try {
            connection = DatabaseFactory.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertSavingAccount(SavingAccount savingAccount) throws SQLException {
        connection.setAutoCommit(false);

        try {
            String accountSql = "INSERT INTO account (account_id, user_id, bank, agency, account_number, balance) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement accountStmt = connection.prepareStatement(accountSql);
            accountStmt.setInt(1, savingAccount.getAccountId());
            accountStmt.setString(2, savingAccount.getUserId());
            accountStmt.setString(3, savingAccount.getBank());
            accountStmt.setString(4, savingAccount.getAgency());
            accountStmt.setString(5, savingAccount.getAccount());
            accountStmt.setBigDecimal(6, savingAccount.getBalance());
            accountStmt.executeUpdate();

            String savingSql = "INSERT INTO saving_account (account_id, tax) VALUES (?, ?)";
            PreparedStatement savingStmt = connection.prepareStatement(savingSql);
            savingStmt.setInt(1, savingAccount.getAccountId());
            savingStmt.setBigDecimal(2, savingAccount.getTax());
            savingStmt.executeUpdate();

            connection.commit(); // Confirma as operações

        } catch (SQLException e) {
            connection.rollback(); // Reverte se algo falhar
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restaura comportamento padrão
        }
    }

    public List<SavingAccount> getAllSavingAccounts() throws SQLException {
        List<SavingAccount> savingAccounts = new ArrayList<>();

        String sql = """
            SELECT 
                a.account_id, a.user_id, a.bank, a.agency, a.account_number, a.balance, 
                s.tax
            FROM account a
            JOIN saving_account s ON a.account_id = s.account_id
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
            BigDecimal tax = rs.getBigDecimal("tax");

            SavingAccount savingAccount = new SavingAccount(accountId, userId, bank, agency, accountNumber, balance, tax);
            savingAccounts.add(savingAccount);
        }

        return savingAccounts;
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
