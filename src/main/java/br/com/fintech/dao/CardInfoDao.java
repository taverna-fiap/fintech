package br.com.fintech.dao;

import br.com.fintech.config.DatabaseFactory;
import br.com.fintech.models.entities.concrete.CardInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardInfoDao {
    private Connection connection;

    public CardInfoDao() {
        try {
            connection = DatabaseFactory.connect();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    public void insertCard(CardInfo card) throws SQLException {
        String sql = "INSERT INTO CardInfo (cardId, cardName, cardNumber, cvv, expiration, paymentDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, card.getCardId());
            st.setString(2, card.getCardName());
            st.setString(3, card.getCardNumber());
            st.setString(4, card.getCvv());
            st.setString(5, card.getExpiration());
            st.setString(6, card.getPaymentDate());
            st.executeUpdate();
        }
    }

    public List<CardInfo> getAllCards() throws SQLException {
        List<CardInfo> cards = new ArrayList<>();
        String sql = "SELECT * FROM CardInfo";

        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                CardInfo card = new CardInfo();
                card.setCardId(rs.getString("cardId"));
                card.setCardName(rs.getString("cardName"));
                card.setCardNumber(rs.getString("cardNumber"));
                card.setCvv(rs.getString("cvv"));
                card.setExpiration(rs.getString("expiration"));
                card.setPaymentDate(rs.getString("paymentDate"));
                cards.add(card);
            }
        }

        return cards;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar conexão", e);
        }
    }
}
