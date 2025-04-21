package br.com.fintech.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "rm559953";
    private static final String PASS = "220304";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }


}
