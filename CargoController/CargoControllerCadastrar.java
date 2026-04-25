package CargoController;

import CargoDAO.CargoDAOCadastrar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;

@WebServlet(name = "CargoControllerCadastrar", urlPatterns = {"/CargoControllerCadastrar"})
public class CargoControllerCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        String mensagem = "";

        try (PrintWriter out = response.getWriter()) {

            if ("CADASTRAR_CARGO".equals(op)) {
                try {
                    Cargo c = new Cargo();
                    c.setNome(request.getParameter("nome"));
                    c.setFuncao(request.getParameter("funcao"));
                    c.setSalarioBase(Double.parseDouble(request.getParameter("salarioBase")));

                    CargoDAOCadastrar cdaocad = new CargoDAOCadastrar();
                    cdaocad.cadastrar(c);        // Não precisa mais capturar o retorno

                    mensagem = "Cargo cadastrado com sucesso!";

                } catch (Exception ex) {
                    mensagem = "ERRO ao cadastrar cargo: " + ex.getMessage();
                    ex.printStackTrace();
                }

                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("cadastrar.jsp").forward(request, response);  // ajuste o nome da JSP se necessário

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