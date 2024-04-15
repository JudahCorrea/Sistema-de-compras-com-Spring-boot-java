package br.market.market_sistem.Model;


import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;


public class Carrinho {

    ArrayList<Produto> produtos;

    public Carrinho(){
        this.produtos = new ArrayList<>();
    }

    public Carrinho(ArrayList<Produto> produtos) {
        //super();
        this.produtos = produtos;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto (int id){
        Produto mp = null;
        for (Produto p : this.produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return mp;
    }

    public void removeProduto (int id){
        Produto p = getProduto(id);
        produtos.remove(p);
    }
    
    public void addProduto (Produto p){
        produtos.add(p);
    }
}

