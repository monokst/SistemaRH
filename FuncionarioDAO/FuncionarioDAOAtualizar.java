package FuncionarioDAO;

import CargoDAO.CargoDAOAtualizar;
import EnderecoDAO.EnderecoDAOAtualizar;
import java.sql.*;
import model.Funcionario;
import util.Conexao;

public class FuncionarioDAOAtualizar {

    public void atualizar(Funcionario f) throws ClassNotFoundException, SQLException {
        
        Connection con = Conexao.getConexao();
        
        try {
            // Atualiza Endereço
            if (f.getEndereco() != null) {
                EnderecoDAOAtualizar endDao = new EnderecoDAOAtualizar();
                endDao.atualizar(f.getEndereco());
            }

            // Atualiza Cargo
            if (f.getCargo() != null) {
                CargoDAOAtualizar cargoDao = new CargoDAOAtualizar();
                cargoDao.atualizar(f.getCargo());
            }

            // Atualiza Funcionário
            String sql = "UPDATE funcionario SET nome=?, cpf=?, data_nascimento=?, " +
                        "data_admissao=?, genero=?, telefone=?, email=?, " +
                        "cargo_id=?, endereco_id=? WHERE id_fun=?";

            PreparedStatement comando = con.prepareStatement(sql);
            
            comando.setString(1, f.getNome());
            comando.setString(2, f.getCpf());
            comando.setDate(3, new java.sql.Date(f.getDataDeNascimento().getTime()));
            comando.setDate(4, new java.sql.Date(f.getDataDeAdmissao().getTime()));
            comando.setString(5, f.getGenero());
            comando.setString(6, f.getTelefone());
            comando.setString(7, f.getEmail());
            comando.setInt(8, f.getCargo().getId_cargo());
            comando.setInt(9, f.getEndereco().getId_end());
            comando.setInt(10, f.getId_Fun());

            comando.executeUpdate();

        } finally {
            con.close();
        }
    }
}