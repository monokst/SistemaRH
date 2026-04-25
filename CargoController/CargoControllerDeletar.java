/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CargoController;

import CargoDAO.CargoDAODeletar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;

@WebServlet(name = "CargoControllerDeletar", urlPatterns = {"/CargoControllerDeletar"})
public class CargoControllerDeletar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");
            String mensagem = "";
            CargoDAODeletar cdaodel = new CargoDAODeletar();

            Cargo c = new Cargo();

            if (op.equals("DELETAR_CARGO")) {
                try {
                    int id_cargo = Integer.parseInt(request.getParameter("id_cargo"));
                    c.setId_cargo(id_cargo);
                    cdaodel.deletar(c);
                    mensagem = "Deletado com sucesso!";
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "ERRO: " + ex.getMessage();
                }
                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("deletar.jsp").forward(request, response);
            }
        }
    }
}
