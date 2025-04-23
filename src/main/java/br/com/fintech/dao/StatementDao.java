package br.com.fintech.dao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.Statement;

import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatementDao {
    final private Connection connection;

    public StatementDao() {
        try {
            connection = DatabaseFactory.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertStatement(Statement statement) throws SQLException {
        String sql = "INSERT INTO Statement (statementId,transactionId, amount, timestamp, status, account) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, statement.getStatementId());
        st.setString(2, statement.getTransactionId());
        st.setBigDecimal(3, statement.getAmount());
        st.setTimestamp(4, Timestamp.valueOf(statement.getTimestamp()));
        st.setString(5, statement.getStatus());
        st.setString(6, statement.getAccount());

        st.executeUpdate();
    }

    public List<Statement> getAllStatements() throws SQLException {
        List<Statement> statements = new ArrayList<>();

        String sql = "SELECT * FROM Statement";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String transactionId = rs.getString("transactionId");
            BigDecimal amount = rs.getBigDecimal("amount");
            LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
            String status = rs.getString("status");
            String account = rs.getString("account");

            Statement statement = new Statement(transactionId, amount, timestamp, status, account);
            statements.add(statement);
        }

        return statements;
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
