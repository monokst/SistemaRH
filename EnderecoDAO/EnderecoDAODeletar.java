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
public class EnderecoDAODeletar {
        public void deletar(int id_end) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement(
            "delete from endereco where id_end = ?"
        );
        comando.setInt(1, id_end);
        comando.execute();
        con.close();
    }
}
