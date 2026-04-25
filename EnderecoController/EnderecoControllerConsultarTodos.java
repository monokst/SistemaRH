package EnderecoController;

import EnderecoDAO.EnderecoDAOConsultarTodos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;

@WebServlet(name = "EnderecoControllerConsultarTodos", urlPatterns = {"/EnderecoControllerConsultarTodos"})
public class EnderecoControllerConsultarTodos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");
            String mensagem = "";
            EnderecoDAOConsultarTodos edaotodos = new EnderecoDAOConsultarTodos();

            if (op.equals("CONSULTAR_TODOS_ENDERECO")) {
                try {
                    List<Endereco> lista = edaotodos.consultarTodos();
                    request.setAttribute("lista", lista);
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "ERRO: " + ex.getMessage();
                    request.setAttribute("msg", mensagem);
                }
                request.getRequestDispatcher("EnderecoConsultarTodos.jsp").forward(request, response);
            }
        }
    }
}