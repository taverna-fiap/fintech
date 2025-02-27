import Models.*;

public class Main {

    public static void main(String[] args) {
        User toUser = new User("Teste", "teste@teste.com", "000", "000", "teste", "000");
        Account toAccount = new Account ("1", "1111", "1111", "1111", "x");
        CardInfo toCardInfo = new CardInfo("1", "Teste", "111", "111", "11/1111", "11/11/1111");
        Tag toTag = new Tag("1", "Teste");
        Category toCategory = new Category("1", "Teste");
        Transaction toTransaction = new Transaction("1", "Teste", "200", "11/11/1111", true);

        System.out.println(toUser.getName());
        System.out.println(toAccount.getAccountId());
        System.out.println(toCardInfo.getCardId());
        System.out.println(toTag.getTagName());
        System.out.println(toCategory.getCategoryId());
        System.out.println(toTransaction.getValue());
    }
}




