package br.market.market_sistem.Model;

import java.net.URISyntaxException;
import java.sql.*;

public class ClienteDAO {

    public ClienteDAO(){

    }
    
    public Cliente confirmClienteCredentials(String email, String password){
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Cliente c = null;

        try{
            connection = Conexao.getConnection();
            p = connection.prepareStatement("SELECT * FROM Cliente WHERE email=? AND senha=?");
            p.setString(1, email);
            p.setString(2, password);

            rs = p.executeQuery();
        
            while(rs.next()) {
                c = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
            }

            connection.close();

        }catch(SQLException | URISyntaxException exception){
                //throw new RuntimeException("Erro ao buscar Cliente por email e senha", exception);
                c = null;
        }
        return c;
    }

    public boolean checkEmailExistence(String email){
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Cliente c = null;

        try{
            connection = Conexao.getConnection();
            p = connection.prepareStatement("SELECT * FROM Cliente WHERE email=?");
            p.setString(1, email);

            rs = p.executeQuery();

            while(rs.next()){
                c = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
            }

            connection.close();

        }catch(SQLException | URISyntaxException exception){
            throw new RuntimeException("Erro ao buscar Cliente por email e senha", exception);
            //c = null;
        }
        if(c == null){ // se permanecer null significa que a busca no bd nao encontrou nenhum email igual
            return false;
        }
        return true;
    }


    public void insertClientBD(Cliente cliente){
        Connection connection = null;
        PreparedStatement p = null;

        try{
            connection = Conexao.getConnection();
            p = connection.prepareStatement("INSERT INTO Cliente (nome, email, senha) VALUES (?, ?, ?)");
            p.setString(1, cliente.getNome());
            p.setString(2, cliente.getEmail());
            p.setString(3, cliente.getSenha());
            p.execute();
            connection.close();
        }catch(SQLException | URISyntaxException exception ){
            throw new RuntimeException("Erro ao buscar Cliente por email e senha", exception);
        }
    }
}
