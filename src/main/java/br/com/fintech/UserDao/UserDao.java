package br.com.fintech.UserDao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    private Connection connection;

    public UserDao() {
        try {
            connection = DatabaseFactory.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUser(User user) throws SQLException {
        PreparedStatement st = connection.prepareStatement("INSERT INTO Users (id, name, email, password, cpf, phone, address, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        st.setString(1, user.getId());
        st.setString(2, user.getName());
        st.setString(3, user.getEmail());
        st.setString(4, user.getPassword());
        st.setString(5, user.getCpf());
        st.setString(6, user.getPhone());
        st.setString(7, user.getAddress());
        st.setString(8, user.getBirthday());
        st.executeUpdate();
    }

}
