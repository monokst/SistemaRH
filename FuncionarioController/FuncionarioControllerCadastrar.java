package FuncionarioController;

import CargoDAO.CargoDAOCadastrar;
import EnderecoDAO.EnderecoDAOCadastrar;
import FuncionarioDAO.FuncionarioDAOCadastrar;
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

@WebServlet(name = "FuncionarioControllerCadastrar", urlPatterns = {"/FuncionarioControllerCadastrar"})
public class FuncionarioControllerCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        String mensagem = "";

        if ("CADASTRAR_FUNCIONARIO".equals(op)) {

            try {
                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String dataNascStr = request.getParameter("dataDeNascimento");
                String dataAdmStr = request.getParameter("dataDeAdmissao");
                String nomeCargo = request.getParameter("nomeCargo");
                String funcao = request.getParameter("funcao");
                String salarioStr = request.getParameter("salarioBase");
                String rua = request.getParameter("rua");
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");
                String estado = request.getParameter("estado");
                String cepStr = request.getParameter("cep");

                // Validações...
                if (nome == null || nome.trim().isEmpty()) {
                    mensagem = "ERRO: Nome completo é obrigatório.";
                } else if (cpf == null || cpf.trim().isEmpty()) {
                    mensagem = "ERRO: CPF é obrigatório.";
                } else if (dataNascStr == null || dataNascStr.trim().isEmpty()) {
                    mensagem = "ERRO: Data de Nascimento é obrigatória.";
                } else if (dataAdmStr == null || dataAdmStr.trim().isEmpty()) {
                    mensagem = "ERRO: Data de Admissão é obrigatória.";
                } else if (nomeCargo == null || nomeCargo.trim().isEmpty()) {
                    mensagem = "ERRO: Nome do Cargo é obrigatório.";
                } else if (funcao == null || funcao.trim().isEmpty()) {
                    mensagem = "ERRO: Função do cargo é obrigatória.";
                } else if (salarioStr == null || salarioStr.trim().isEmpty()) {
                    mensagem = "ERRO: Salário Base é obrigatório.";
                } else if (rua == null || rua.trim().isEmpty()) {
                    mensagem = "ERRO: Rua é obrigatória.";
                } else if (bairro == null || bairro.trim().isEmpty()) {
                    mensagem = "ERRO: Bairro é obrigatório.";
                } else if (cidade == null || cidade.trim().isEmpty()) {
                    mensagem = "ERRO: Cidade é obrigatória.";
                } else if (estado == null || estado.trim().isEmpty()) {
                    mensagem = "ERRO: Estado é obrigatório.";
                } else if (cepStr == null || cepStr.trim().isEmpty()) {
                    mensagem = "ERRO: CEP é obrigatório.";
                }

                if (!mensagem.isEmpty()) {
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
                    return;
                }

                // ====================== CADASTRO ======================
                Endereco endereco = new Endereco();
                endereco.setRua(rua);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                endereco.setCep(Integer.parseInt(cepStr));

                EnderecoDAOCadastrar endDao = new EnderecoDAOCadastrar();
                endDao.cadastrar(endereco);

                Cargo cargo = new Cargo();
                cargo.setNome(nomeCargo);
                cargo.setFuncao(funcao);
                cargo.setSalarioBase(Double.parseDouble(salarioStr));

                CargoDAOCadastrar cargoDao = new CargoDAOCadastrar();
                cargoDao.cadastrar(cargo);

                // ✅ CORREÇÃO: Formato correto vindo do input type="date"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dataNasc = sdf.parse(dataNascStr);
                Date dataAdm = sdf.parse(dataAdmStr);

                Funcionario f = new Funcionario.FuncionarioBuilder(
                        nome, 
                        cpf, 
                        dataAdm, 
                        cargo)
                        .dataDeNascimento(dataNasc)
                        .endereco(endereco)
                        .genero(request.getParameter("genero"))
                        .telefone(request.getParameter("telefone"))
                        .email(request.getParameter("email"))
                        .build();

                FuncionarioDAOCadastrar funDao = new FuncionarioDAOCadastrar();
                funDao.cadastrar(f);

                mensagem = "Funcionário cadastrado com sucesso!";

            } catch (NumberFormatException e) {
                mensagem = "ERRO: Formato inválido de número (CEP ou Salário).";
            } catch (java.text.ParseException e) {
                mensagem = "ERRO: Formato de data inválido.";
                e.printStackTrace();
            } catch (Exception ex) {
                mensagem = "ERRO inesperado ao cadastrar: " + ex.getMessage();
                ex.printStackTrace();
            }
        }

        request.setAttribute("msg", mensagem);
        request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
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

                