package br.com.fintech.localTests;

import br.com.fintech.dao.TransactionDao;
import br.com.fintech.models.entities.concrete.Statement;
import br.com.fintech.models.entities.concrete.Transaction;
import br.com.fintech.models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionTest {
    public static void test() {
        try {
            TransactionDao transactionDao = new TransactionDao();

            Transaction novaTransacao = new Transaction();
            novaTransacao.setTransactionId(UUID.randomUUID().toString());
            novaTransacao.setAmount(new BigDecimal("250.75"));
            novaTransacao.setTimestamp(LocalDateTime.now());
            novaTransacao.setStatus(TransactionStatus.COMPLETED);
           //novaTransacao.setAccount(new Account("acc001"));
           // novaTransacao.setStatement(new Statement(1L));

            try {
                transactionDao.insertTransaction(novaTransacao);
                System.out.println("Transação inserida com sucesso!");

                List<Transaction> transacoes = transactionDao.getAllTransactions();
                transacoes.forEach(System.out::println);

            } catch (SQLException e) {
                System.out.println("Erro ao inserir transação: " + e.getMessage());
                throw e;
            } finally {
                transactionDao.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste de transação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
