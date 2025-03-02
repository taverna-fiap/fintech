package main;

import models.entities.*;
import models.enums.TransactionStatus;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criando categorias
        Category categoriaAlimentacao = new Category("1", "Alimentação");
        Category categoriaTransporte = new Category("2", "Transporte");

        // Criando uma conta bancária
        Account conta = new Account("123456", "Banco XYZ", "0001", "12345-6", new BigDecimal("1000.00"));

        // Criando um usuário
        User usuario = new User(1, "João Silva", "joao@email.com", "123.456.789-00", "11999999999", "Rua Exemplo, 123", "01/01/1990", List.of(conta));


        System.out.println("\n=== Criando uma transação válida ===");
        Transaction transacao1 = new Transaction(new BigDecimal("200.00"), conta, categoriaAlimentacao);
        transacao1.process();
        System.out.println(transacao1);

        // Criando uma transação inválida (saldo insuficiente)
        System.out.println("\n=== Criando uma transação inválida (saldo insuficiente) ===");
        Transaction transacao2 = new Transaction(new BigDecimal("900.00"), conta, categoriaTransporte);
        transacao2.process();
        System.out.println(transacao2);

        // Exibir o saldo atualizado da conta
        System.out.println("\n=== Saldo atualizado da conta ===");
        System.out.println(conta);

        // Exibir os logs das transações
        System.out.println("\n=== Logs das transações ===");
        for (TransactionLog log : conta.getTransactionLogs()) {
            System.out.println(log);
        }
    }
}
