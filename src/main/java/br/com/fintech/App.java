package br.com.fintech;
import br.com.fintech.config.DatabaseFactory;

public class App {
    public static void main( String[] args ) {
        try {
            DatabaseFactory.connect();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

    }
}
