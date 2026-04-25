package FuncionarioController;

import FuncionarioDAO.FuncionarioDAOConsultarTodos;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;

@WebServlet(name = "RelatorioController", urlPatterns = {"/RelatorioController"})
public class RelatorioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        String mensagem = "";

        if ("DASHBOARD".equals(op)) {
            try {
                FuncionarioDAOConsultarTodos dao = new FuncionarioDAOConsultarTodos();
                List<Funcionario> lista = dao.consultarTodos();

                if (lista == null || lista.isEmpty()) {
                    mensagem = "Nenhum funcionário encontrado no sistema.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("relatorios.jsp").forward(request, response);
                    return;
                }

                int totalFuncionarios = lista.size();

                // Contagem por gênero
                int masculino = 0, feminino = 0, outro = 0;

                // Cálculo da Média Salarial
                double somaSalarios = 0.0;
                int countSalariosValidos = 0;

                // Contagem por Cargo
                Map<String, Integer> porCargo = new HashMap<>();

                for (Funcionario f : lista) {
                    // Gênero
                    String genero = (f.getGenero() != null) ? f.getGenero().trim().toLowerCase() : "";
                    if (genero.contains("masculino")) masculino++;
                    else if (genero.contains("feminino")) feminino++;
                    else outro++;

                    // Média Salarial - Correção aqui
                    if (f.getCargo() != null) {
                        somaSalarios += f.getCargo().getSalarioBase();
                        countSalariosValidos++;
                    }

                    // Por Cargo
                    String cargoNome = (f.getCargo() != null && f.getCargo().getNome() != null)
                            ? f.getCargo().getNome() : "Sem cargo";
                    porCargo.put(cargoNome, porCargo.getOrDefault(cargoNome, 0) + 1);
                }

                double mediaSalarial = (countSalariosValidos > 0) ? (somaSalarios / countSalariosValidos) : 0.0;

                // Envia os dados para a JSP
                request.setAttribute("lista", lista);
                request.setAttribute("totalFuncionarios", totalFuncionarios);
                request.setAttribute("masculino", masculino);
                request.setAttribute("feminino", feminino);
                request.setAttribute("outro", outro);
                request.setAttribute("mediaSalarial", mediaSalarial);
                request.setAttribute("porCargo", porCargo);

            } catch (Exception ex) {
                mensagem = "ERRO inesperado ao gerar o relatório: " + ex.getMessage();
                ex.printStackTrace();
                request.setAttribute("msg", mensagem);
            }
        } else {
            mensagem = "ERRO: Operação inválida.";
            request.setAttribute("msg", mensagem);
        }

        request.getRequestDispatcher("relatorios.jsp").forward(request, response);
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