package FuncionarioController;

import FuncionarioDAO.FuncionarioDAOAtualizar;
import FuncionarioDAO.FuncionarioDAOPorID;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;
import model.Endereco;
import model.Funcionario;

@WebServlet(name = "FuncionarioControllerAtualizar", urlPatterns = {"/FuncionarioControllerAtualizar"})
public class FuncionarioControllerAtualizar extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        String mensagem = "";
 
        try {
            // ====================== CLICOU NO BOTÃO "EDITAR" DA LISTA ======================
            if (op == null || !op.equals("ATUALIZAR_FUNCIONARIO")) {
                int id_fun = Integer.parseInt(request.getParameter("id_fun"));
 
                FuncionarioDAOPorID daoPorId = new FuncionarioDAOPorID();
                Funcionario funcionario = daoPorId.buscarPorId(id_fun);
 
                if (funcionario == null) {
                    mensagem = "Funcionário não encontrado.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("atualizarpage.jsp").forward(request, response);
                    return;
                }
 
                // Envia o funcionário para a página de atualização
                request.setAttribute("funcionario", funcionario);
                request.getRequestDispatcher("atualizarpage.jsp").forward(request, response);
                return;
            }
 
            // ====================== CLICOU NO BOTÃO "ATUALIZAR" DO FORMULÁRIO ======================
            if ("ATUALIZAR_FUNCIONARIO".equals(op)) {
                int id_fun = Integer.parseInt(request.getParameter("id_fun"));
 
                // Busca o funcionário atual para manter referências
                FuncionarioDAOPorID daoPorId = new FuncionarioDAOPorID();
                Funcionario funcionarioExistente = daoPorId.buscarPorId(id_fun);
 
                if (funcionarioExistente == null) {
                    mensagem = "Funcionário não encontrado para atualização.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("atualizarpage.jsp").forward(request, response);
                    return;
                }
 
                // Atualiza Endereço
                Endereco endereco = funcionarioExistente.getEndereco();
                endereco.setRua(request.getParameter("rua"));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCidade(request.getParameter("cidade"));
                endereco.setEstado(request.getParameter("estado"));
                endereco.setCep(Integer.parseInt(request.getParameter("cep")));
 
                // Atualiza Cargo
                Cargo cargo = funcionarioExistente.getCargo();
                cargo.setNome(request.getParameter("nomeCargo"));
                cargo.setFuncao(request.getParameter("funcao"));
                cargo.setSalarioBase(Double.parseDouble(request.getParameter("salarioBase")));
 
                // Monta Funcionário atualizado
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dataNasc = sdf.parse(request.getParameter("dataDeNascimento"));
                Date dataAdm = sdf.parse(request.getParameter("dataDeAdmissao"));
 
                Funcionario f = new Funcionario.FuncionarioBuilder(
                        request.getParameter("nome"),
                        request.getParameter("cpf"),
                        dataAdm,
                        cargo)
                        .id_fun(id_fun)
                        .dataDeNascimento(dataNasc)
                        .endereco(endereco)
                        .genero(request.getParameter("genero"))
                        .telefone(request.getParameter("telefone"))
                        .email(request.getParameter("email"))
                        .build();
 
                // Atualiza no banco
                FuncionarioDAOAtualizar daoAtualizar = new FuncionarioDAOAtualizar();
                daoAtualizar.atualizar(f);
 
                mensagem = "Funcionário atualizado com sucesso!";
            }
 
        } catch (Exception ex) {
            mensagem = "ERRO ao atualizar: " + ex.getMessage();
            ex.printStackTrace();
        }
 
        request.setAttribute("msg", mensagem);
        request.getRequestDispatcher("FuncionarioControllerConsultarTodos?op=CONSULTAR_TODOS_FUNCIONARIO").forward(request, response);
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