package br.com.fintech.services;

import br.com.fintech.models.entities.concrete.Transaction;

public class TransactionService {
    public void processTransaction(Transaction transaction) {
        System.out.println("Processando transação: " + transaction);
        transaction.process();
        System.out.println("Transação finalizada: " + transaction);
    }
}
