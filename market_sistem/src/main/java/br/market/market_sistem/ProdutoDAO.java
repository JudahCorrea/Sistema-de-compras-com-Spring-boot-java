package br.market.market_sistem;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
