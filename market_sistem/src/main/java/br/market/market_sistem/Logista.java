package br.market.market_sistem;

public class Logista {
    private int id;
    private String email;
    private String nome;
    private String senha;

    public Logista(){

    }

    public Logista(int id, String email, String nome, String senha){
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha; 
    }

    public int getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
