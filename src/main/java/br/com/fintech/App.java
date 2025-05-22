package br.com.fintech;


import br.com.fintech.localTests.*;


public class App {
    public static void main( String[] args ) {
        UserTest.test();
        CardInfoTest.test();
        StatementTest.test();
        TransactionTest.test();
        TransactionLogTest.test();
        CheckingAccountTest.test();
        SavingAccountTest.test();

    }
}
