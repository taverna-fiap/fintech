package br.com.fintech.localTests;

import br.com.fintech.dao.StatementDao;
import br.com.fintech.models.entities.concrete.Statement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;

public class StatementTest {
    public static void test() {
        try {
            StatementDao statementDao = new StatementDao();

            Statement novoStatement = new Statement(
                    "TX-20250421-01",
                    new BigDecimal("1250.75"),
                    LocalDateTime.now(),
                    "SUCESSO",
                    "ACC-4456"
            );

            try {
                statementDao.insertStatement(novoStatement);
                System.out.println("Extrato inserido com sucesso!");

                List<Statement> lista = statementDao.getAllStatements();
                lista.forEach(System.out::println);

            } catch (SQLException e) {
                System.err.println("Erro ao inserir extrato: " + e.getMessage());
                throw e;
            } finally {
                statementDao.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste de extrato: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
