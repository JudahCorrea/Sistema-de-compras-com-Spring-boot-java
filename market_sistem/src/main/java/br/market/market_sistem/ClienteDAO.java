package br.market.market_sistem;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public ClienteDAO(){

    }
    
    public boolean confirmClienteCredentials(String email, String password){
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
        
        while(rs.next()){
            c = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
        }

        connection.close();

        }catch(SQLException | URISyntaxException exception){
            //throw new RuntimeException("Erro ao buscar Cliente por email e senha", exception);
            c = null;
        }
        if(c != null){
            return true;
        }
        return false;
    }
}
