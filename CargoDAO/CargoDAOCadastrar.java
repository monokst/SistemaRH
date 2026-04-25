package CargoDAO;

import java.sql.*;
import model.Cargo;
import util.Conexao;

public class CargoDAOCadastrar {
    public int cadastrar(Cargo c) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement(
            "INSERT INTO cargo (nome, funcao, salarioBase) VALUES (?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );

        comando.setString(1, c.getNome());
        comando.setString(2, c.getFuncao());
        comando.setDouble(3, c.getSalarioBase());

        comando.executeUpdate();

        ResultSet rs = comando.getGeneratedKeys();
        int idGerado = 0;
        if (rs.next()) {
            idGerado = rs.getInt(1);
            c.setId_cargo(idGerado);
        }

        con.close();
        return idGerado;
    }
}