package br.market.market_sistem;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LojistaDAO {

    public LojistaDAO(){

    }
    
    public boolean confirmLojistaCredentials(String email, String password){
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Lojista l = null;

        try{
            connection = Conexao.getConnection();

            p = connection.prepareStatement("SELECT * FROM Lojista WHERE email=? AND senha=?");
            p.setString(1,email);
            p.setString(2,password);

            rs = p.executeQuery();

            while (rs.next()) {
                l = new Lojista(rs.getInt("id_lojista"),rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
            }

            connection.close();

        }catch(SQLException | URISyntaxException exception){
            //throw new RuntimeException("Erro ao buscar lojista por email e senha", exception);
            l = null;
        }

        if(l != null){
            return true;
        }
        return false;
    }
}
