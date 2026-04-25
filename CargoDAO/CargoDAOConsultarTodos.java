/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CargoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cargo;
import util.Conexao;

/**
 *
 * @author thamy
 */
public class CargoDAOConsultarTodos {

    public List<Cargo> consultarTodos() throws ClassNotFoundException, SQLException {

        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM cargo");
        ResultSet rs = comando.executeQuery();

        List<Cargo> lista = new ArrayList<>();

        while (rs.next()) {
            Cargo car = new Cargo(
                rs.getInt("id_cargo"),  
                rs.getString("nome"),
                rs.getString("funcao"),
                rs.getDouble("salarioBase")
            );

            lista.add(car);
        }

        con.close();
        return lista;
    }
}
