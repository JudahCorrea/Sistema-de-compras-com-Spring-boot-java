package br.market.market_sistem.Model;

public class Lojista {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Lojista(){

    }

    public Lojista(int id, String nome,String email , String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha; 
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
