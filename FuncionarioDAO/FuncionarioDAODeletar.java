package FuncionarioDAO;

import CargoDAO.CargoDAODeletar;
import EnderecoDAO.EnderecoDAODeletar;
import java.sql.*;
import model.Funcionario;
import util.Conexao;

public class FuncionarioDAODeletar {

    public void deletar(int id_fun) throws ClassNotFoundException, SQLException {
        
        Connection con = Conexao.getConexao();
        
        try {
            FuncionarioDAOPorID busca = new FuncionarioDAOPorID();
            Funcionario f = busca.buscarPorId(id_fun);

            if (f != null) {
                // Deleta Funcionário
                String sql = "DELETE FROM funcionario WHERE id_fun = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id_fun);
                ps.executeUpdate();

                // Deleta Endereço
                if (f.getEndereco() != null) {
                    EnderecoDAODeletar endDao = new EnderecoDAODeletar();
                    endDao.deletar(f.getEndereco().getId_end());
                }

                // Deleta Cargo
                if (f.getCargo() != null) {
                    CargoDAODeletar cargoDao = new CargoDAODeletar();
                    cargoDao.deletar(f.getCargo());
                }
            }
        } finally {
            con.close();
        }
    }
}