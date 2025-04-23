package br.com.fintech.dao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.TransactionLog;
import br.com.fintech.models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionLogDao {
    private final Connection connection;

    public TransactionLogDao() {
        try {
            connection = DatabaseFactory.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertTransactionLog(TransactionLog log) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO TransactionLog (transactionId, status, amount, timestamp) VALUES (?, ?, ?, ?)"
        );
        st.setString(1, log.getTransactionId());
        st.setString(2, log.getStatus().name());
        st.setBigDecimal(3, log.getAmount());
        st.setTimestamp(4, Timestamp.valueOf(log.getTimestamp()));

        st.executeUpdate();
    }

    public List<TransactionLog> getAllTransactionLogs() throws SQLException {
        List<TransactionLog> logs = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement("SELECT * FROM TransactionLog");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String transactionId = rs.getString("transactionId");
            TransactionStatus status = TransactionStatus.valueOf(rs.getString("status"));
            BigDecimal amount = rs.getBigDecimal("amount");

            TransactionLog log = new TransactionLog(transactionId, status, amount);
            logs.add(log);
        }

        return logs;
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
