package FuncionarioDAO;

import java.sql.*;
import model.Cargo;
import model.Endereco;
import model.Funcionario;
import util.Conexao;

public class FuncionarioDAOPorID {
    public Funcionario buscarPorId(int id_fun) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement(
            "SELECT f.*, c.id_cargo, c.nome AS cargo_nome, c.funcao, c.salarioBase, " +
            "e.id_end, e.rua, e.bairro, e.cidade, e.estado, e.cep " +
            "FROM funcionario f, cargo c, endereco e " +
            "WHERE f.cargo_id = c.id_cargo " +
            "AND f.endereco_id = e.id_end " +
            "AND f.id_fun = ?"
        );

        comando.setInt(1, id_fun);
        ResultSet rs = comando.executeQuery();

        Funcionario f = null;
        if (rs.next()) {
            Cargo cargo = new Cargo(
                rs.getInt("id_cargo"),
                rs.getString("cargo_nome"),
                rs.getString("funcao"),
                rs.getDouble("salarioBase")
            );

            Endereco endereco = new Endereco(
                rs.getInt("id_end"),
                rs.getString("rua"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getInt("cep")
            );

            f = new Funcionario.FuncionarioBuilder(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_admissao"),
                    cargo)
                    .id_fun(rs.getInt("id_fun"))
                    .dataDeNascimento(rs.getDate("data_nascimento"))
                    .endereco(endereco)
                    .genero(rs.getString("genero"))
                    .telefone(rs.getString("telefone"))
                    .email(rs.getString("email"))
                    .build();
        }
        con.close();
        return f;
    }
}