package CargoController;

import CargoDAO.CargoDAOPorID;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;

@WebServlet(name = "CargoControllerPorID", urlPatterns = {"/CargoControllerPorID"})
public class CargoControllerPorID extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String mensagem = "";
            CargoDAOPorID cdao = new CargoDAOPorID();
            Cargo c = new Cargo();

            try {
                int id_cargo = Integer.parseInt(request.getParameter("txtid"));
                c.setId_cargo(id_cargo);

                Cargo cargo = cdao.buscarPorId(id_cargo);

                if (cargo.getId_cargo() == 0) {
                    mensagem = "Cargo não encontrado.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                } else {
                    request.setAttribute("cargo", cargo);
                    request.getRequestDispatcher("CargoconsultarPorID.jsp").forward(request, response);
                }

            } catch (ClassNotFoundException | SQLException ex) {
                mensagem = "ERRO: " + ex.getMessage();
                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
