package br.market.market_sistem.Model;

import br.market.market_sistem.Model.Conexao;
import br.market.market_sistem.Model.Produto;

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
                Produto produto = new Produto(r.getInt("id_produto"), r.getFloat("preco"), r.getString("nome"), r.getString("descricao"), r.getInt("estoque"));
                lista.add(produto);
            }

        }catch(SQLException | URISyntaxException exception){
            throw new RuntimeException("Erro ao listar produto", exception);
        }
        return lista;
    }

    public Produto getProdutoById(int id){
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet r = null;
        Produto produto = null;
        try{
            connection = Conexao.getConnection();
            p = connection.prepareStatement("SELECT * FROM Produto WHERE id_produto = ?");
            p.setInt(1, id);
            r = p.executeQuery();

            while(r.next()){
                Produto pr = new Produto(r.getInt("id_produto"), r.getFloat("preco"), r.getString("nome"), r.getString("descricao"), r.getInt("estoque"));
                produto = pr;
            }
        }catch(SQLException | URISyntaxException exception){
            throw new RuntimeException("Erro ao buscar produto", exception);
        }
        return produto;
    }
<<<<<<< HEAD:market_sistem/src/main/java/br/market/market_sistem/Model/ProdutoDAO.java

=======
>>>>>>> 369dcf1bc4a460483bbd6f96830825a475d53d07:market_sistem/src/main/java/br/market/market_sistem/ProdutoDAO.java
}
