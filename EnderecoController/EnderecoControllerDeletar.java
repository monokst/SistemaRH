package EnderecoController;

import EnderecoDAO.EnderecoDAODeletar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;


@WebServlet(name = "EnderecoControllerDeletar", urlPatterns = {"/EnderecoControllerDeletar"})
public class EnderecoControllerDeletar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");
            String mensagem = "";
            EnderecoDAODeletar edaodel = new EnderecoDAODeletar();

            Endereco e = new Endereco();

            if (op.equals("DELETAR_ENDERECO")) {
                try {
                    int id_end = Integer.parseInt(request.getParameter("id_end"));
                    e.setId_end(id_end);

                    edaodel.deletar(id_end);

                    mensagem = "Deletado com sucesso!";

                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "ERRO: " + ex.getMessage();
                }

                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("EnderecoDeletar.jsp").forward(request, response);
            }
        }
    }
}