package br.com.fintech.localTests;

import br.com.fintech.dao.CheckingAccountDao;
import br.com.fintech.dao.UserDao;
import br.com.fintech.models.entities.concrete.CheckingAccount;
import br.com.fintech.models.entities.concrete.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CheckingAccountTest {
    public static void test() {
        String userId = UUID.randomUUID().toString();

        try {
            UserDao userDao = new UserDao();
            CheckingAccountDao accountDao = new CheckingAccountDao();

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

                var userIdField = User.class.getDeclaredField("id");
                userIdField.setAccessible(true);
                userIdField.set(novoUsuario, userId);

                userDao.insertUser(novoUsuario);
                System.out.println("Usuário inserido com sucesso.");
            } else {
                System.out.println("Usuário já existente. Pulando inserção.");
            }

            int accountId = new Random().nextInt(1000);

            CheckingAccount novaConta = new CheckingAccount(
                    accountId,
                    userId,
                    "Banco Exemplo",
                    "001",
                    "12345678-9",
                    new BigDecimal("1500.00")
            );

            accountDao.insertCheckingAccount(novaConta); // <- método correto agora
            System.out.println("Conta corrente inserida com sucesso!");

            List<CheckingAccount> contas = accountDao.getAllCheckingAccounts(); // <- nome consistente com DAO
            contas.forEach(System.out::println);

            userDao.closeConnection();
            accountDao.closeConnection();

        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste da conta corrente: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
