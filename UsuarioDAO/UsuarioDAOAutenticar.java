/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import util.Conexao;

/**
 *
 * @author thamy
 */
public class UsuarioDAOAutenticar {
     public Usuario autenticar(String email, String senha) throws ClassNotFoundException, SQLException {
        // Obtém a conexão com o banco
        Connection con = Conexao.getConexao();
        // Prepara a query de autenticação
        PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
        // Define os parâmetros da query
        comando.setString(1, email);
        comando.setString(2, senha);
     
        // Executa a query e obtém o resultado
        ResultSet rs = comando.executeQuery();
        // Cria um objeto Usuario para armazenar os dados
        Usuario usuario = null;
        if (rs.next()) {
            // Preenche os dados do usuário
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
        }
        // Fecha a conexão
        con.close();
        return usuario;
    }
}
