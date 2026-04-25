/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CargoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cargo;
import util.Conexao;

/**
 *
 * @author thamy
 */public class CargoDAOPorID {
public Cargo buscarPorId(int id_cargo) throws ClassNotFoundException, SQLException {
    Connection con = Conexao.getConexao();
    PreparedStatement comando = con.prepareStatement(
        "select id_cargo, nome, funcao, salarioBase from cargo where id_cargo = ?"
    );
    comando.setInt(1, id_cargo);
    ResultSet rs = comando.executeQuery();
    Cargo car = null;
    if (rs.next()) {
        car = new Cargo(
            rs.getInt("id_cargo"),
            rs.getString("nome"),
            rs.getString("funcao"),
            rs.getDouble("salarioBase")
        );
    }
    con.close();
    return car;
}
 }