package FuncionarioController;

import FuncionarioDAO.FuncionarioDAODeletar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FuncionarioControllerDeletar", urlPatterns = {"/FuncionarioControllerDeletar"})
public class FuncionarioControllerDeletar extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        String mensagem = "";

        try {
            if ("DELETAR_FUNCIONARIO".equals(op)) {

                String idStr = request.getParameter("id_fun");

                if (idStr == null || idStr.trim().isEmpty()) {
                    mensagem = "ID do funcionário não informado.";
                } else {
                    int id_fun = Integer.parseInt(idStr.trim());

                    FuncionarioDAODeletar dao = new FuncionarioDAODeletar();
                    dao.deletar(id_fun);

                    mensagem = "Funcionário deletado com sucesso!";
                }
            }
        } catch (NumberFormatException e) {
            mensagem = "ERRO: ID inválido.";
        } catch (Exception ex) {
            mensagem = "ERRO ao deletar: " + ex.getMessage();
            ex.printStackTrace();
        }

        // ✅ Após deletar (sucesso ou erro), redireciona para a lista de funcionários
        request.setAttribute("msg", mensagem);
        response.sendRedirect("FuncionarioControllerConsultarTodos?op=CONSULTAR_TODOS_FUNCIONARIO");
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