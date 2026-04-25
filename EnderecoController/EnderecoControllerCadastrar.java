package EnderecoController;

import EnderecoDAO.EnderecoDAOCadastrar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;

@WebServlet(name = "EnderecoControllerCadastrar", urlPatterns = {"/EnderecoControllerCadastrar"})
public class EnderecoControllerCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        String mensagem = "";

        try (PrintWriter out = response.getWriter()) {

            if ("CADASTRAR_ENDERECO".equals(op)) {
                try {
                    Endereco e = new Endereco();
                    e.setRua(request.getParameter("rua"));
                    e.setBairro(request.getParameter("bairro"));
                    e.setCidade(request.getParameter("cidade"));
                    e.setEstado(request.getParameter("estado"));
                    e.setCep(Integer.parseInt(request.getParameter("cep")));

                    EnderecoDAOCadastrar edaocad = new EnderecoDAOCadastrar();
                    edaocad.cadastrar(e);        // Agora não precisa mais do retorno

                    mensagem = "Endereço cadastrado com sucesso!";

                } catch (Exception ex) {
                    mensagem = "ERRO ao cadastrar endereço: " + ex.getMessage();
                    ex.printStackTrace();
                }

                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("EnderecoCadastrar.jsp").forward(request, response);
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