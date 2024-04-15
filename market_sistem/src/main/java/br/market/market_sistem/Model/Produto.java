package br.market.market_sistem.Model;

public class Produto {

    private int id;
    private float preco;
    private String nome;
    private String descricao;
    private int estoque;

    public Produto(){
        
    }


    public Produto(int id, float preco, String nome, String descricao, int estoque) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
        this.estoque = estoque;
    }
        
    public int getId() {
        return this.id;
    }

    public float getPreco() {
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

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEstoque(int estoque){
        this.estoque = estoque;
    }

    public void incrementaEstoque() {
        this.estoque++;
    }

    public void diminuiEstoque() {
        this.estoque--;
    }    
}
