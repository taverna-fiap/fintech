package br.com.fintech.localTests;

import br.com.fintech.dao.TransactionLogDao;
import br.com.fintech.models.entities.concrete.TransactionLog;
import br.com.fintech.models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionLogTest {
    public static void test() {
        try {
            TransactionLogDao dao = new TransactionLogDao();

            TransactionLog log = new TransactionLog(
                    UUID.randomUUID().toString(),
                    TransactionStatus.COMPLETED,
                    new BigDecimal("1200.50")
            );

            try {
                dao.insertTransactionLog(log);
                System.out.println("Log de transação inserido com sucesso!");

                List<TransactionLog> logs = dao.getAllTransactionLogs();
                logs.forEach(System.out::println);
            } catch (SQLException e) {
                System.out.println("Erro ao inserir log de transação: " + e.getMessage());
                throw e;
            } finally {
                dao.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste de TransactionLog: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
