package br.com.fintech.localTests;

import br.com.fintech.dao.UserDao;
import br.com.fintech.models.entities.concrete.User;
import java.sql.SQLException;
import java.util.List;


public class UserTest {
    public static void test(){
        try {
            UserDao userDao = new UserDao();

            User novoUsuario = new User(
                    "Gustavo Henrique",
                    "gustavo@email.com",
                    "12345678900",
                    "(12)99669-4456",
                    "Rua Exemplo, 123",
                    "2003-04-21",
                    "senhaSegura"
            );

            try {
                userDao.insertUser(novoUsuario);
                System.out.println("Usuário inserido com sucesso!");

                List<User> users = userDao.getAllUsers();
                users.forEach(System.out::println);

            } catch (SQLException e) {
                System.out.println("Erro ao inserir usuário: " + e.getMessage());
                throw e;
            } finally {
                userDao.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste de usuário: " + e.getMessage());
            e.printStackTrace();
        }

    }
}