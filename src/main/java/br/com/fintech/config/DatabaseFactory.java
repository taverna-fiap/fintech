package br.com.fintech.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseFactory {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "";
    private static final String PASS = "";


    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        initializeSchema(connection);
        return connection;
    }

    private static void initializeSchema(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE Users (
                                    id VARCHAR2(36) PRIMARY KEY,
                                    name VARCHAR2(100),
                                    email VARCHAR2(100),
                                    password VARCHAR2(100),
                                    cpf VARCHAR2(14),
                                    phone VARCHAR2(20),
                                    address VARCHAR2(200),
                                    birthday VARCHAR2(12)
                                )';
                        EXCEPTION WHEN OTHERS THEN
                            IF SQLCODE != -955 THEN RAISE; END IF;
                        END;
                    """);

            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE CardInfo (
                                    cardId VARCHAR2(36) PRIMARY KEY,
                                    cardName VARCHAR2(100),
                                    cardNumber VARCHAR2(20),
                                    cvv VARCHAR2(4),
                                    expiration VARCHAR2(10),
                                    paymentDate VARCHAR2(10)
                                )';
                        EXCEPTION WHEN OTHERS THEN
                            IF SQLCODE != -955 THEN RAISE; END IF;
                        END;
                    """);

            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE Statement (
                                    statementId VARCHAR2(36) PRIMARY KEY,
                                    transactionId VARCHAR2(36),
                                    amount NUMBER(15, 2),
                                    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    status VARCHAR2(20),
                                    account VARCHAR2(20)
                                )';
                        EXCEPTION
                            WHEN OTHERS THEN
                                IF SQLCODE != -955 THEN
                                    RAISE;
                                END IF;
                        END;
                    """);
            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE Transaction (
                                    transactionId VARCHAR2(36) PRIMARY KEY,
                                    amount NUMBER(15, 2),
                                    timestamp TIMESTAMP,
                                    status VARCHAR2(20),
                                    account VARCHAR2(20),
                                    statementId VARCHAR2(36)
                                )';
                        EXCEPTION WHEN OTHERS THEN
                            IF SQLCODE != -955 THEN RAISE; END IF;
                        END;
                    """);

            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE TransactionLog (
                                    transactionId VARCHAR2(36) PRIMARY KEY,
                                    status VARCHAR2(20),
                                    amount NUMBER(15, 2),
                                    timestamp TIMESTAMP
                                )';
                        EXCEPTION WHEN OTHERS THEN
                            IF SQLCODE != -955 THEN RAISE; END IF;
                        END;
                    """);

            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE Account (
                                    account_id NUMBER PRIMARY KEY,
                                    user_id VARCHAR2(36),
                                    bank VARCHAR2(100),
                                    agency VARCHAR2(20),
                                    account_number VARCHAR2(20),
                                    balance NUMBER(15,2),
                                    FOREIGN KEY (user_id) REFERENCES Users(id)
                                )';
                        EXCEPTION WHEN OTHERS THEN
                            IF SQLCODE != -955 THEN RAISE; END IF;
                        END;
                    """);

            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE Checking_Account (
                                    account_id NUMBER PRIMARY KEY,
                                    special_credit NUMBER(15,2),
                                    FOREIGN KEY (account_id) REFERENCES Account(account_id)
                                )';
                        EXCEPTION WHEN OTHERS THEN
                            IF SQLCODE != -955 THEN RAISE; END IF;
                        END;
                    """);

            stmt.executeUpdate("""
                        BEGIN
                            EXECUTE IMMEDIATE '
                                CREATE TABLE Saving_Account (
                                    account_id NUMBER PRIMARY KEY,
                                    tax NUMBER(5,2),
                                    FOREIGN KEY (account_id) REFERENCES Account(account_id)
                                )';
                        EXCEPTION WHEN OTHERS THEN
                            IF SQLCODE != -955 THEN RAISE; END IF;
                        END;
                    """);

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
