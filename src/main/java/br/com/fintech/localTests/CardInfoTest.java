package br.com.fintech.localTests;

import br.com.fintech.dao.CardInfoDao;
import br.com.fintech.models.entities.concrete.CardInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CardInfoTest {
    public static void test() {
        try {
            CardInfoDao cardInfoDao = new CardInfoDao();

            CardInfo novoCartao = new CardInfo(
                    UUID.randomUUID().toString(),
                    "Cartão Nubank",
                    "1234-5678-9012-3456",
                    "123",
                    "2028-12",
                    "2025-04-21"
            );

            try {
                cardInfoDao.insertCard(novoCartao);
                System.out.println("Cartão inserido com sucesso!");

                List<CardInfo> cards = cardInfoDao.getAllCards();
                cards.forEach(System.out::println);

            } catch (SQLException e) {
                System.err.println("Erro ao inserir cartão: " + e.getMessage());
                throw e;
            } finally {
                cardInfoDao.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste de cartão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
