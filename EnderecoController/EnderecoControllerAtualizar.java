package EnderecoController;

import EnderecoDAO.EnderecoDAOAtualizar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;

@WebServlet(name = "EnderecoControllerAtualizar", urlPatterns = {"/EnderecoControllerAtualizar"})
public class EnderecoControllerAtualizar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");
            String mensagem = "";
            EnderecoDAOAtualizar edaoatu = new EnderecoDAOAtualizar();

            Endereco e = new Endereco();

            if (op.equals("ATUALIZAR_ENDERECO")) {
                try {
                    int id_end = Integer.parseInt(request.getParameter("id_end"));
                    String rua = request.getParameter("rua");
                    String bairro = request.getParameter("bairro");
                    String cidade = request.getParameter("cidade");
                    String estado = request.getParameter("estado");
                    int cep = Integer.parseInt(request.getParameter("cep"));

                    e.setId_end(id_end);
                    e.setRua(rua);
                    e.setBairro(bairro);
                    e.setCidade(cidade);
                    e.setEstado(estado);
                    e.setCep(cep);

                    edaoatu.atualizar(e);
                    mensagem = "Atualizado com sucesso!";

                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "ERRO: " + ex.getMessage();
                }

                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("EnderecoAtualizar.jsp").forward(request, response);
            }
        }
    }
}