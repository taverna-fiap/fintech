package br.com.fintech.dao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.Transaction;
import br.com.fintech.models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final Connection connection;

    public TransactionDao() {
        try {
            connection = DatabaseFactory.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
// precisa refatorar o insertTransaction para inserir IDs validos de uma conta ou extrato
    public void insertTransaction(Transaction transaction) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO Transaction (transactionId, amount, timestamp, status) VALUES (?, ?, ?, ?)"
        );
        st.setString(1, transaction.getTransactionId());
        st.setBigDecimal(2, transaction.getAmount());
        st.setTimestamp(3, Timestamp.valueOf(transaction.getTimestamp()));
        st.setString(4, transaction.getStatus().name());
        //st.setString(5, transaction.getAccount().getAccountId());
        //st.setLong(6, transaction.getStatement().getStatementId());

        st.executeUpdate();
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

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement("SELECT * FROM Transaction");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String transactionId = rs.getString("transactionId");
            BigDecimal amount = rs.getBigDecimal("amount");
            LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
            TransactionStatus status = TransactionStatus.valueOf(rs.getString("status"));
            String accountId = rs.getString("account");
            long statementId = rs.getLong("statementId");


            Transaction transaction = new Transaction();
            transaction.setTransactionId(transactionId);
            transaction.setAmount(amount);
            transaction.setTimestamp(timestamp);
            transaction.setStatus(status);
            //transaction.setAccount(new Account(accountId));
            //transaction.setStatement(new Statement(statementId));

            transactions.add(transaction);
        }

        return transactions;
    }
}
