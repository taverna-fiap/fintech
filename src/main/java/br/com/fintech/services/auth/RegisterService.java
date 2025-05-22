package br.com.fintech.services.auth;

import br.com.fintech.models.entities.concrete.User;
import java.util.ArrayList;
import java.util.List;

public class RegisterService {
    private static final List<User> users = new ArrayList<>();

    public static User registerUser(String name, String email,String password,String confirmPassword ,String cpf, String phone, String address, String birthday ) {
        if(!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Verifica se o e-mail já está cadastrado
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException("E-mail já cadastrado.");
            }
        }

        User newUser = new User(name, email,password ,cpf, phone, address, birthday);
        users.add(newUser);
        System.out.println(newUser);
        System.out.println("Registro realizado com sucesso!");
        return newUser;
    }

    public static List<User> getUsers() {
        return users;
    }
}
