package FuncionarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cargo;
import model.Endereco;
import model.Funcionario;
import util.Conexao;

public class FuncionarioDAOConsultarTodos {
    public List<Funcionario> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement(
            "SELECT f.*, c.id_cargo, c.nome AS cargo_nome, c.funcao, c.salarioBase, " +
            "e.id_end, e.rua, e.bairro, e.cidade, e.estado, e.cep " +
            "FROM funcionario f, cargo c, endereco e " +
            "WHERE f.cargo_id = c.id_cargo AND f.endereco_id = e.id_end"
        );

        ResultSet rs = comando.executeQuery();
        List<Funcionario> lista = new ArrayList<>();

        while (rs.next()) {
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

            Funcionario f = new Funcionario.FuncionarioBuilder(
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

            lista.add(f);
        }
        con.close();
        return lista;
    }
}