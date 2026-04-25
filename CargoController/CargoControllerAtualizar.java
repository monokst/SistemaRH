/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CargoController;

import CargoDAO.CargoDAOAtualizar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;

@WebServlet(name = "CargoControllerAtualizar", urlPatterns = {"/CargoControllerAtualizar"})
public class CargoControllerAtualizar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");
            String mensagem = "";
            CargoDAOAtualizar cdaoatu = new CargoDAOAtualizar();

            Cargo c = new Cargo();
            if (op.equals("ATUALIZAR_CARGO")) {
                try {
                    int id_cargo = Integer.parseInt(request.getParameter("id_cargo"));
                    String nome = request.getParameter("nome");
                    String funcao = request.getParameter("funcao");
                    Double salarioBase = Double.parseDouble(request.getParameter("salarioBase"));

                    c.setId_cargo(id_cargo);
                    c.setNome(nome);
                    c.setFuncao(funcao);
                    c.setSalarioBase(salarioBase);

                    cdaoatu.atualizar(c);
                    mensagem = "Atualizado com sucesso!";
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "ERRO: " + ex.getMessage();
                }
                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("atualizar.jsp").forward(request, response);

            }
        }
    }
}
