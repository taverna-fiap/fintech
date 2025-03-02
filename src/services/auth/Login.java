package services.auth;
import models.entities.User;
import java.util.Scanner;

public class Login {
    public static void doLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login ===");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        for (User user : RegisterService.getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Login realizado com sucesso! Bem-vindo, " + user.getName());
                return;
            }
        }

        System.out.println("Erro: Usuário não encontrado.");
    }
}
