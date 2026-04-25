package FuncionarioController;

import FuncionarioDAO.FuncionarioDAOConsultarTodos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;

@WebServlet(name = "FuncionarioControllerConsultarTodos", urlPatterns = {"/FuncionarioControllerConsultarTodos"})
public class FuncionarioControllerConsultarTodos extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        String mensagem = "";

        try {
            if ("CONSULTAR_TODOS_FUNCIONARIO".equals(op)) {

                FuncionarioDAOConsultarTodos dao = new FuncionarioDAOConsultarTodos();
                List<Funcionario> lista = dao.consultarTodos();

                // Envia a lista para o JSP
                request.setAttribute("lista", lista);

                // Se houver mensagem de sucesso/erro vinda de outro controller (cadastro, update, delete)
                String msgRecebida = (String) request.getAttribute("msg");
                if (msgRecebida != null) {
                    mensagem = msgRecebida;
                }

                request.getRequestDispatcher("consultartodos.jsp").forward(request, response);
                return;
            }
        } catch (Exception ex) {
            mensagem = "ERRO ao consultar funcionários: " + ex.getMessage();
            ex.printStackTrace();
        }

        // Caso de erro ou se não entrou no if
        request.setAttribute("msg", mensagem);
        request.getRequestDispatcher("consultartodos.jsp").forward(request, response);
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