package CargoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cargo;
import util.Conexao;

/**
 *
 * @author thamy
 */
public class CargoDAOAtualizar {
    public void atualizar (Cargo c) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update cargo set nome = ?, funcao = ?, salariobase = ? where id_cargo = ?");
        comando.setString(1, c.getNome());
        comando.setString(2, c.getFuncao());
        comando.setDouble(3, c.getSalarioBase());
        comando.setInt(4, c.getId_cargo());
        comando.execute();
        con.close();
    }
}
