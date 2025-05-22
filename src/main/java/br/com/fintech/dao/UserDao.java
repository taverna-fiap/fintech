package br.com.fintech.dao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final Connection connection;

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

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement("SELECT * FROM Users");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String cpf = rs.getString("cpf");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String birthday = rs.getString("birthday");

            User user = new User(name, email, cpf, phone, address, birthday, password);
            users.add(user);
        }

        return users;
    }


}
