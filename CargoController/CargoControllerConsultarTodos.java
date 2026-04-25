package CargoController;

import CargoDAO.CargoDAOConsultarTodos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;

@WebServlet(name = "CargoControllerConsultarTodos", urlPatterns = {"/CargoControllerConsultarTodos"})
public class CargoControllerConsultarTodos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");
            String mensagem = "";
            CargoDAOConsultarTodos cdaotodos = new CargoDAOConsultarTodos();

            Cargo c = new Cargo();

            if (op.equals("CONSULTAR_TODOS_CARGO")) {
                try {
                    List<Cargo> lista = cdaotodos.consultarTodos();
                    request.setAttribute("lista", lista);
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "ERRO: " + ex.getMessage();
                    request.setAttribute("msg", mensagem);
                }
                request.getRequestDispatcher("consultarTodos.jsp").forward(request, response);
            }
        }
    }
}
