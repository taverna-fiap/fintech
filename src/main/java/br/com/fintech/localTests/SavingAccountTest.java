package br.com.fintech.localTests;

import br.com.fintech.dao.SavingAccountDao;
import br.com.fintech.dao.UserDao;
import br.com.fintech.models.entities.concrete.SavingAccount;
import br.com.fintech.models.entities.concrete.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SavingAccountTest {
    public static void test() {
        String userId = UUID.randomUUID().toString();

        try {
            UserDao userDao = new UserDao();
            SavingAccountDao accountDao = new SavingAccountDao();

            List<User> existingUsers = userDao.getAllUsers();
            boolean userExists = existingUsers.stream().anyMatch(u -> u.getId().equals(userId));

            if (!userExists) {
                User novoUsuario = new User(
                        "Usuário Teste",
                        "teste@email.com",
                        "00011122233",
                        "(11)91234-5678",
                        "Rua dos Testes, 999",
                        "1990-01-01",
                        "senha123"
                );

                // Força o ID fixo no usuário
                var userIdField = User.class.getDeclaredField("id");
                userIdField.setAccessible(true);
                userIdField.set(novoUsuario, userId);

                userDao.insertUser(novoUsuario);
                System.out.println("Usuário inserido com sucesso.");
            } else {
                System.out.println("Usuário já existente. Pulando inserção.");
            }

            SavingAccount novaContaPoupanca = new SavingAccount(
                    new Random().nextInt(1000),
                    userId,
                    "Banco Exemplo",
                    "001",
                    "12345678-9",
                    new BigDecimal("2000.00"),
                    new BigDecimal("0.05") // Taxa de 5% de rendimento
            );

            accountDao.insertSavingAccount(novaContaPoupanca);
            System.out.println("Conta poupança inserida com sucesso!");

            List<SavingAccount> contasPoupanca = accountDao.getAllSavingAccounts();
            contasPoupanca.forEach(System.out::println);

            userDao.closeConnection();
            accountDao.closeConnection();

        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste da conta poupança: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
