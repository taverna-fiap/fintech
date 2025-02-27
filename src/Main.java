

public class Main {

    public static void main(String[] args) {
        Person toPerson = new Person("Teste", "teste@teste.com", "000", "000", "teste", "000");
        Account toAccount = new Account ("1", "1111", "1111", "1111", "x");
        Card toCard = new Card  ("1", "Teste", "111", "111", "11/1111", "11/11/1111");
        Type toType = new Type ("1", "Teste");
        Category toCategory = new Category("1", "Teste");
        Transaction toTransaction = new Transaction("1", "Teste", "200", "11/11/1111", true);

        System.out.println(toPerson.getName());
        System.out.println(toAccount.getAccountId());
        System.out.println(toCard.getCardId());
        System.out.println(toType.getTypeName());
        System.out.println(toCategory.getCategoryId());
        System.out.println(toTransaction.getValue());
    }
}




