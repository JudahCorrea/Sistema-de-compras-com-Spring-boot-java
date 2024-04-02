package br.market.market_sistem;

public class Produto {

    private int id;
    private int preco;
    private String nome;
    private String descricao;
    private int estoque;

    public Produto(int id, int preco, String nome, String descricao, int estoque) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
        this.estoque = estoque;
    }
        
    public int getId() {
        return this.id;
    }

    public int getPreco() {
        return this.preco;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getEstoque() {
        return this.estoque;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void incrementaEstoque() {
        this.estoque++;
    }

    public void diminuiEstoque() {
        this.estoque--;
    }    
}
