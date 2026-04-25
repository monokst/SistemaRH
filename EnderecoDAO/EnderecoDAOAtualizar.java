/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EnderecoDAO;

import model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Conexao;

/**
 *
 * @author thamy
 */
public class EnderecoDAOAtualizar {
    public void atualizar(Endereco e) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement(
            "update endereco set rua = ?, bairro = ?, cidade = ?, estado = ?, cep = ? where id_end = ?"
        );
        comando.setString(1, e.getRua());
        comando.setString(2, e.getBairro());
        comando.setString(3, e.getCidade());
        comando.setString(4, e.getEstado());
        comando.setInt(5, e.getCep());
        comando.setInt(6, e.getId_end());
        comando.execute();
        con.close();
    }
    
}
