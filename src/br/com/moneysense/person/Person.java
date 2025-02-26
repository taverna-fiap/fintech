package br.com.moneysense.person;

public class Person {

        private int id;
        private String name;
        private String email;
        private String cpf;
        private String phone;
        private String adress;
        private String birthay;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBirthay() {
        return birthay;
    }

    public void setBirthay(String birthay) {
        this.birthay = birthay;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //criando objeto pessoa
        public Person(String name, String email, String cpf, String phone, String adress, String birthay) {
            this.name = name;
            this.email = email;
            this.cpf = cpf;
            this.phone = phone;
            this.adress = adress;
            this.birthay = birthay;
        }

    @Override
    public String toString() {
        return super.toString();
    }
}
