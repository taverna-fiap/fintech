package models.entities;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String address;
    private String birthday;
    private List<Account> accounts;

    public User(int id, String name, String email, String cpf, String phone, String address, String birthday, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
