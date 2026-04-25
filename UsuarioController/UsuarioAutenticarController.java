package UsuarioController;

import UsuarioDAO.UsuarioDAOAutenticar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Usuario;

@WebServlet(name = "UsuarioAutenticarController", urlPatterns = {"/UsuarioAutenticarController"})
public class UsuarioAutenticarController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String op = request.getParameter("op");
            String mensagem = "";
            UsuarioDAOAutenticar udao = new UsuarioDAOAutenticar();
            Usuario u = new Usuario();

            if (op.equals("Autenticar")) {

                try {
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");

                    u.setEmail(email);
                    u.setSenha(senha);

                    Usuario usuarioLogado = udao.autenticar(email, senha);

                    if (usuarioLogado != null) {
                        // cria sessão
                        HttpSession session = request.getSession();
                        session.setAttribute("usuario", usuarioLogado);

                        response.sendRedirect("homepage.html");
                    } else {
                        mensagem = "Email ou senha inválidos!";
                        request.setAttribute("msg", mensagem);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "ERRO: " + ex.getMessage();
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
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