package br.market.market_sistem;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public ProdutoDAO(){

    }
    
    public void addProduto(Produto produto){
        Connection connection = null;
        PreparedStatement p = null;
        
        try{
            connection = Conexao.getConnection();
            p = connection.prepareStatement("INSERT INTO Produto (preco, nome, descricao, estoque) VALUES (?,?,?,?)");
            p.setFloat(1, produto.getPreco());
            p.setString(2, produto.getNome());
            p.setString(3,produto.getDescricao());
            p.setInt(4,produto.getEstoque());
            p.executeUpdate();
            connection.close();
        }catch(SQLException | URISyntaxException exception){
            throw new RuntimeException("Erro ao cadastrar produto", exception);
        }
    }

    public List<Produto> listarProdutos(){
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet r = null;
        List<Produto> lista = new ArrayList<>();

        try{
            connection = Conexao.getConnection();
            p = connection.prepareStatement("SELECT * FROM Produto");
            r = p.executeQuery();

            while(r.next()){
                Produto pr = new Produto(r.getInt("id_produto"), r.getFloat("preco"), r.getString("nome"), r.getString("descricao"), r.getInt("estoque"));
                lista.add(pr);
            }

        }catch(SQLException | URISyntaxException exception){
            throw new RuntimeException("Erro ao listar produto", exception);
        }
        return lista;
    }
}
