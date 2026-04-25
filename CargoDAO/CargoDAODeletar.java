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
public class CargoDAODeletar {
        public void deletar(Cargo c) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("delete from cargo where id_cargo = ?");
        comando.setInt(1, c.getId_cargo());
        comando.execute();
        con.close();
    }
}
