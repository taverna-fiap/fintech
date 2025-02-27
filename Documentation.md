# 📂 Estrutura das Classes

---

## 🧑‍💼 User

### Descrição:  
Representa um usuário do sistema, contendo suas informações pessoais, sua conta e seu histórico de transações.

### 📌 Atributos:

- **id** (String) – Identificador único do usuário.
- **name** (String) – Nome do usuário.
- **cpf** (String) – CPF do usuário.
- **birthDate** (String) – Data de nascimento (ex.: "dd/MM/yyyy").
- **phone** (String) – Telefone de contato.
- **address** (String) – Endereço residencial.
- **account** (Account) – Conta associada ao usuário.
- **transactionLogs** (List<TransactionLog>) – Histórico de transações realizadas.

### 🔧 Métodos:

- **Getters e setters** – Acessam e modificam os atributos.
- **➕ addTransactionLog(TransactionLog log)** – Adiciona uma entrada ao histórico de transações.
- **➕ addAccount(Account account)** – Associa uma conta ao usuário.

---

## 🏦 Account

Descrição:**  
Representa uma conta bancária vinculada a um usuário.

### 📌 Atributos:

- **accountNumber** (String) – Número único da conta.
- **balance** (int) – Saldo da conta (armazenado em centavos).
- **user** (User) – Usuário proprietário da conta.

### 🔧 Métodos:

- **➕ deposit(int amount)** – Adiciona um valor (em centavos) ao saldo.
- **➖ withdraw(int amount)** – Realiza um saque, verificando se há saldo suficiente.
- **➕ addTransaction(Transaction transaction)** – Adiciona uma transação ao histórico da conta.
- **💡 getBalance()** – Retorna o saldo atual (em centavos).
- **💡 getConvertedBalance()** – Converte o saldo para reais (divide por 100) e retorna um valor do tipo double.
- **Getters e setters** – Métodos de acesso aos atributos.

---

## 💳 Transaction

### Descrição:  
Representa uma transação financeira realizada em uma conta.

### 📌 Atributos:

- **transactionId** (String) – Identificador único da transação.
- **amount** (double) – Valor da transação.
- **type** (String) – Tipo da transação (ex.: "DEPOSIT", "WITHDRAW").
- **timestamp** (LocalDateTime) – Data e hora da transação.
- **user** (User) – Usuário que realizou a transação.

### 🔧 Métodos:

- **✔ validateTransaction()** – Valida se a transação pode ser processada.
- **Getters e setters** – Acessam os atributos.

---

## 📜 TransactionLog

### Descrição:
Registra uma operação (log) referente a uma transação realizada por um usuário.

### 📌 Atributos:

- **logId** (String) – Identificador único do log.
- **transaction** (Transaction) – Referência para a transação registrada.
- **message** (String) – Mensagem descritiva do log.

### 🔧 Métodos:

- **Getters e setters** – Acessam e modificam os atributos.

> **Nota:** O método para adicionar logs é implementado na classe **User**.

---

## 🏷 Tag

### Descrição:  
Define uma etiqueta para categorizar as transações.

### 📌 Atributos:

- **typeId** (String) – Identificador único da tag.
- **name** (String) – Nome da tag (ex.: "Pix", "TED", "Boleto").

### 🔧 Métodos:

- **Getters e setters** – Acessam os atributos.
- **getTagInfo()** – Retorna informações da tag.

---

## 📂 Category

### Descrição:  
Classifica as transações em categorias para organizar os gastos e receitas.

### 📌 Atributos:

- **categoryId** (String) – Identificador único da categoria.
- **name** (String) – Nome da categoria (ex.: "Alimentação", "Transporte", "Salário").

### 🔧 Métodos:

- **Getters e setters** – Acessam os atributos.
- **getCategoryInfo()** – Retorna os detalhes da categoria.

---

## 🧾 Statement

### Descrição: 
Gera e representa o extrato bancário de uma conta para um determinado período.

### 📌 Atributos:

- **statementId** (String) – Identificador único do extrato.
- **account** (Account) – Conta associada ao extrato.
- **transactions** (List<Transaction>) – Lista de transações incluídas no extrato.
- **period** (String) – Período do extrato (ex.: "Jan/2025").

### 🔧 Métodos:

- **generateStatement()** – Gera o extrato com base nas transações do período.
- **getStatementDetails()** – Retorna os detalhes do extrato.
- **Getters e setters** – Métodos de acesso aos atributos.

<p style="font-size: 0px">a</p>

---

## 🔧 Serviços:
<p style="font-size: 0px">a</p>

---

## 🔑 IdempotencyKeyGenerator

### Descrição:
Responsável por gerar chaves únicas para evitar o processamento duplicado de transações.

### 🔧 Métodos:

- **🔑 generateKey(Transaction transaction)** – Gera uma chave única com base nos atributos da transação (por exemplo, combinando usuário, valor e timestamp arredondado para 4 minutos).

---

## ⚙️ TransactionService

### Descrição: 
Gerencia o processamento das transações, aplicando regras de idempotência.

### 🔧 Métodos:

- **⚙️ processTransaction(Transaction transaction)** – Verifica a chave de idempotência e processa a transação se não for duplicada.
- **❗ isDuplicate(Transaction transaction)** – Checa se a transação já foi registrada nos últimos 4 minutos.
- **💾 storeTransaction(Transaction transaction)** – Registra a transação no log do usuário.

---

## 🔐 AuthenticateService

### Descrição:  
Gerencia os processos de autenticação, incluindo login e registro de usuários.

### 📌 Atributos:

- **users** (List<User>) – Lista de usuários cadastrados (pode ser gerenciada via DataService).

### 🔧 Métodos:**

- **register(String name, String email, String password, String cpf, String birthDate, String phone, String address)**  
  ➜ Registra um novo usuário no sistema.
- **login(String email, String password)**  
  ➜ Autentica um usuário, validando suas credenciais.
- **logout(User user)**  
  ➜ Encerra a sessão do usuário.
- **isAuthenticated(User user)**  
  ➜ Verifica se o usuário está autenticado.