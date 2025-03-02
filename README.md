# Fintech Project

## 1. Visão Geral

O Fintech Project é uma simulação de sistema financeiro desenvolvido em Java, criado para os requisitos acadêmicos da FIAP. O projeto demonstra conceitos fundamentais da Programação Orientada a Objetos (POO), como encapsulamento, modularidade, e práticas de design como idempotência. O sistema abrange a gestão de usuários, contas bancárias, transações financeiras, categorização (tags e categorias), geração de extratos e serviços de autenticação.

---

## 2. Objetivo

**Intuito e Objetivo:**
- **Objetivo:** Desenvolver uma aplicação que simule as operações básicas de um sistema financeiro (Fintech), permitindo o registro, autenticação e gerenciamento de usuários, bem como a realização e registro de transações financeiras.
- **Intuito:** Demonstrar a aplicação de conceitos avançados de POO, organização modular e a implementação de mecanismos de idempotência, assegurando que operações duplicadas não sejam processadas indevidamente.

---

## 3. Tecnologias Utilizadas

- **Linguagem:** Java (versão 21 da JDK)
- **Paradigma:** Programação Orientada a Objetos (POO)
- **Ferramentas:** IntelliJ IDEA (ou outra IDE Java de sua preferência)
- **Estruturas de Dados:** Listas, Mapas e demais coleções padrão do Java
- **Princípios Aplicados:** Encapsulamento, modularidade, idempotência

---

## 4. Estrutura do Projeto

### Entidades (models)
- **User:** Contém informações pessoais, conta e histórico de transações do usuário.
- **Account:** Representa a conta bancária do usuário, com saldo armazenado (em centavos) e operações de depósito/saque.
- **Transaction:** Registra os detalhes de uma transação financeira, incluindo valor, tipo, data e usuário envolvido.
- **TransactionLog:** Armazena registros (logs) referentes às transações realizadas.
- **Tag:** Define etiquetas para categorizar transações (ex.: "Pix", "TED", "Boleto").
- **Category:** Classifica as transações em categorias (ex.: "Alimentação", "Transporte").
- **Statement:** Gera e representa extratos bancários para períodos específicos.

### Serviços
- **IdempotencyKeyGenerator:** Gera chaves únicas para evitar o processamento duplicado de transações.
- **TransactionService:** Gerencia o processamento das transações, aplicando regras de idempotência.
- **AuthenticateService:** Gerencia os processos de autenticação (login e registro) dos usuários.

---

## 5. Como Testar a Aplicação

### Pré-requisitos:
- Ter o Java 17 (ou superior) instalado.
- Utilizar uma IDE compatível (como IntelliJ IDEA ou Eclipse).

### Passos para Execução:
1. **Clonar o Repositório:**
```sh
   git clone https://github.com/seu-usuario/fintech-project.git
   cd fintech-project
```

2. - **Importar o Projeto na IDE:**  
     Abra a IDE e importe o projeto como um projeto Java.

3. **Executar a Classe Principal:**  
   Execute a classe `Main.java` para iniciar a aplicação. Esta classe servirá como ponto de entrada e deverá conter exemplos de:
    - Registro e autenticação de usuários via `AuthenticateService`.
    - Operações bancárias (depósito, saque) realizadas em instâncias de `Account`.
    - Processamento de transações e registro nos logs de cada usuário.
    - Geração de extratos através da classe `Statement`.

4. **Verificar Saída:**  
   A saída será exibida no console, com mensagens que indicam a execução dos métodos (ex.: "Usuário autenticado com sucesso", "Depósito realizado", "Extrato gerado", etc.).

## 6. Considerações Finais

- **Encapsulamento:** Todos os atributos são privados, com métodos de acesso (getters e setters) para garantir a integridade dos dados.
- **Modularidade:** A separação entre modelos e serviços permite uma manutenção mais simples e escalabilidade.
- **Idempotência:** Implementada para evitar o processamento duplicado de transações, garantindo consistência nas operações.
- **Histórico Personalizado:** Cada usuário possui um log individual, facilitando a auditoria e o rastreamento das operações.