package EnderecoController;

import EnderecoDAO.EnderecoDAOPorID;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;

@WebServlet(name = "EnderecoControllerPorID", urlPatterns = {"/EnderecoControllerPorID"})
public class EnderecoControllerPorID extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String mensagem = "";
            EnderecoDAOPorID edao = new EnderecoDAOPorID();
            Endereco e = new Endereco();

            try {
                int id_end = Integer.parseInt(request.getParameter("txtid"));
                e.setId_end(id_end);

                Endereco endereco = edao.buscarPorId(id_end);

                if (endereco.getId_end() == 0) {
                    mensagem = "Endereço não encontrado.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                } else {
                    request.setAttribute("endereco", endereco);
                    request.getRequestDispatcher("EnderecoConsultarPorID.jsp").forward(request, response);
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