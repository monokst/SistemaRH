package FuncionarioController;

import FuncionarioDAO.FuncionarioDAOPorID;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;

@WebServlet(name = "FuncionarioControllerPorID", urlPatterns = {"/FuncionarioControllerPorID"})
public class FuncionarioControllerPorID extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String mensagem = "";

        try (PrintWriter out = response.getWriter()) {

            int id_fun = Integer.parseInt(request.getParameter("id_fun"));

            FuncionarioDAOPorID dao = new FuncionarioDAOPorID();
            Funcionario f = dao.buscarPorId(id_fun);

            if (f == null) {
                mensagem = "Funcionário não encontrado.";
                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("consultarporid.jsp").forward(request, response);
            } else {
                request.setAttribute("funcionario", f);
                request.getRequestDispatcher("consultarporid.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            mensagem = "ERRO: " + ex.getMessage();
            request.setAttribute("msg", mensagem);
            request.getRequestDispatcher("consultarporid.jsp").forward(request, response);
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