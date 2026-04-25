<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Funcionario"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atualizar Funcionário - ekko RH</title>
    <style>
            @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Inter', sans-serif;
                background-color: #f0f2f5;
                min-height: 100vh;
                display: flex;
            }

            /* ========== Sidebar ========== */
            .sidebar {
                width: 250px;
                background-color: #1e293b;
                color: #fff;
                display: flex;
                flex-direction: column;
                min-height: 100vh;
                position: fixed;
                top: 0;
                left: 0;
            }

            .sidebar-header {
                padding: 24px 20px;
                border-bottom: 1px solid rgba(255,255,255,0.08);
            }

            .sidebar-header h1 {
                font-size: 20px;
                font-weight: 700;
            }

            .sidebar-header h1 span {
                color: #818cf8;
            }

            .sidebar-header p {
                font-size: 11px;
                color: #94a3b8;
                margin-top: 2px;
            }

            .sidebar-label {
                font-size: 10px;
                text-transform: uppercase;
                letter-spacing: 1.5px;
                color: #64748b;
                padding: 24px 20px 8px;
            }

            .sidebar-nav {
                list-style: none;
                padding: 10px;
            }

            .sidebar-nav li a {
                display: flex;
                align-items: center;
                gap: 10px;
                padding: 10px 14px;
                color: #cbd5e1;
                text-decoration: none;
                font-size: 14px;
                border-radius: 8px;
                transition: background 0.2s;
            }

            .sidebar-nav li a:hover {
                background: rgba(255,255,255,0.06);
            }

            .sidebar-nav li a.active {
                background: #3b82f6;
                color: #fff;
            }

            .sidebar-nav li a .icon {
                width: 18px;
                text-align: center;
            }

            .sidebar-footer {
                margin-top: auto;
                padding: 20px;
                border-top: 1px solid rgba(255,255,255,0.08);
            }

            .sidebar-footer a {
                color: #ef4444;
                text-decoration: none;
                font-size: 14px;
                display: flex;
                align-items: center;
                gap: 8px;
            }

            /* ========== Main Content ========== */
            .main {
                margin-left: 250px;
                flex: 1;
                padding: 40px 48px;

                display: flex;
                flex-direction: column;
                align-items: center; /* ⭐ ISSO centraliza */
            }

            .page-title {
                font-size: 26px;
                font-weight: 700;
                color: #1e293b;
            }

            .page-subtitle {
                font-size: 14px;
                color: #64748b;
                margin-top: 4px;
                margin-bottom: 32px;
            }

            /* ========== Form Card ========== */
            .form-card {
                background: #fff;
                border-radius: 16px;
                padding: 36px 40px;
                width: 100%;
                max-width: 780px; /* controla largura */
                box-shadow: 0 1px 3px rgba(0,0,0,0.06);
                border: 1px solid #e2e8f0;
            }

            .form-card h2 {
                font-size: 20px;
                font-weight: 600;
                color: #1e293b;
                margin-bottom: 4px;
                display: flex;
                align-items: center;
                gap: 10px;
            }

            .form-card h2 .badge {
                width: 36px;
                height: 36px;
                background: #dbeafe;
                border-radius: 10px;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 18px;
            }

            .form-card .form-desc {
                font-size: 13px;
                color: #94a3b8;
                margin-bottom: 28px;
            }

            /* ========== Form Sections ========== */
            .form-section {
                margin-bottom: 28px;
            }

            .form-section:last-of-type {
                margin-bottom: 0;
            }

            .section-title {
                font-size: 14px;
                font-weight: 600;
                color: #1e293b;
                text-transform: uppercase;
                letter-spacing: 0.5px;
                padding-bottom: 10px;
                margin-bottom: 18px;
                border-bottom: 2px solid #e2e8f0;
                display: flex;
                align-items: center;
                gap: 8px;
            }

            .section-title .section-icon {
                width: 28px;
                height: 28px;
                border-radius: 8px;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 14px;
            }

            .section-icon.blue   {
                background: #dbeafe;
            }
            .section-icon.green  {
                background: #dcfce7;
            }
            .section-icon.orange {
                background: #ffedd5;
            }

            /* ========== Form Grid ========== */
            .form-grid {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 18px;
            }

            .form-grid.three-cols {
                grid-template-columns: 1fr 1fr 1fr;
            }

            .form-group {
                display: flex;
                flex-direction: column;
            }

            .form-group.full {
                grid-column: 1 / -1;
            }

            .form-group label {
                font-size: 13px;
                font-weight: 500;
                color: #334155;
                margin-bottom: 6px;
            }

            .form-group input,
            .form-group select {
                padding: 10px 14px;
                border: 1px solid #e2e8f0;
                border-radius: 10px;
                font-size: 14px;
                font-family: 'Inter', sans-serif;
                color: #1e293b;
                background: #f8fafc;
                transition: border-color 0.2s, box-shadow 0.2s;
                outline: none;
            }

            .form-group input::placeholder {
                color: #94a3b8;
            }

            .form-group input:focus,
            .form-group select:focus {
                border-color: #3b82f6;
                box-shadow: 0 0 0 3px rgba(59,130,246,0.12);
                background: #fff;
            }

            /* ========== Buttons ========== */
            .form-actions {
                display: flex;
                gap: 12px;
                margin-top: 32px;
                justify-content: flex-end;
            }

            .btn {
                padding: 10px 28px;
                border-radius: 10px;
                font-size: 14px;
                font-weight: 600;
                font-family: 'Inter', sans-serif;
                cursor: pointer;
                transition: all 0.2s;
                border: none;
            }

            .btn-primary {
                background: #1e293b;
                color: #fff;
            }

            .btn-primary:hover {
                background: #334155;
            }

            .btn-outline {
                background: transparent;
                text-decoration: none;
                color: #64748b;
                border: 1px solid #e2e8f0;
            }

            .btn-outline:hover {
                background: #f1f5f9;
            }

            /* ========== Responsive ========== */
            @media (max-width: 768px) {
                .sidebar {
                    display: none;
                }
                .main {
                    margin-left: 0;
                    padding: 24px 16px;
                }
                .form-grid,
                .form-grid.three-cols {
                    grid-template-columns: 1fr;
                }
                .form-card {
                    padding: 24px 20px;
                }
            }
        </style>
    </head>
    <body>

        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="sidebar-header">
                <h1><span>ekko</span> RH</h1>
                <p>Gestão de Recursos Humanos</p>
            </div>
            <div class="sidebar-label">Menu Principal</div>
            <ul class="sidebar-nav">
                <li><a href="homepage.html"><span class="icon">🏠</span> Home</a></li>
                <li><a href="FuncionarioControllerConsultarTodos?op=CONSULTAR_TODOS_FUNCIONARIO" class="active"><span class="icon">👤</span> Funcionários</a></li>
                <li><a href="RelatorioController?op=DASHBOARD"><span class="icon">📊</span> Relatórios</a></li>
            </ul>
            <div class="sidebar-footer">
                <a href="logouthome">🚪 Sair</a>
            </div>
        </aside>

    <main class="main">
        <h1 class="page-title">Atualizar Funcionário</h1>
        <p class="page-subtitle">Edite os dados do funcionário abaixo.</p>

        <%-- Mensagem --%>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
                boolean sucesso = msg.toLowerCase().contains("sucesso");
        %>
            <div style="background: <%= sucesso ? "#d4edda" : "#fee2e2" %>; 
                        color: <%= sucesso ? "#155724" : "#991b1b" %>; 
                        padding: 15px 20px; 
                        border-radius: 8px; 
                        margin-bottom: 25px;">
                <%= msg %>
            </div>
        <% } %>

        <div class="form-card">
            <h2><span class="badge">✏️</span> Atualizar Funcionário</h2>

            <form action="FuncionarioControllerAtualizar" method="post">
                <input type="hidden" name="op" value="ATUALIZAR_FUNCIONARIO">

                <%
                    Funcionario f = (Funcionario) request.getAttribute("funcionario");
                    if (f != null) {
                %>
                    <input type="hidden" name="id_fun" value="<%= f.getId_Fun() %>">

                    <!-- Dados Pessoais -->
                    <div class="form-section">
                        <div class="section-title">
                            <span class="section-icon blue">👤</span> Dados Pessoais
                        </div>
                        <div class="form-grid">
                            <div class="form-group full">
                                <label for="nome">Nome Completo</label>
                                <input type="text" id="nome" name="nome" value="<%= f.getNome() %>" required>
                            </div>
                            <!-- CPF adicionado como somente leitura p/ manter intregidade dos dados  -->
                            <div class="form-group">
                                <label for="cpf">CPF</label>
                                <input type="text" id="cpf" name="cpf" 
                                       value="<%= f.getCpf() != null ? f.getCpf() : "" %>" 
                                       readonly style="background: #f1f5f9; cursor: not-allowed;">
                            </div>
                            <div class="form-group">
                                <label for="dataDeNascimento">Data de Nascimento</label>
                                <input type="date" id="dataDeNascimento" name="dataDeNascimento" 
                                       value="<%= f.getDataDeNascimento() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(f.getDataDeNascimento()) : "" %>" required>
                            </div>
                            <div class="form-group">
                                <label for="genero">Gênero</label>
                                <select id="genero" name="genero" required>
                                    <option value="<%= f.getGenero() != null ? f.getGenero() : "" %>" selected><%= f.getGenero() != null ? f.getGenero() : "Selecione" %></option>
                                    <option value="Masculino">Masculino</option>
                                    <option value="Feminino">Feminino</option>
                                    <option value="Outro">Outro</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="telefone">Telefone</label>
                                <input type="tel" id="telefone" name="telefone" value="<%= f.getTelefone() != null ? f.getTelefone() : "" %>">
                            </div>
                            <div class="form-group full">
                                <label for="email">E-mail</label>
                                <input type="email" id="email" name="email" value="<%= f.getEmail() != null ? f.getEmail() : "" %>">
                            </div>
                        </div>
                    </div>

                    <!-- Cargo -->
                    <div class="form-section">
                        <div class="section-title">
                            <span class="section-icon green">💼</span> Cargo
                        </div>
                        <div class="form-grid three-cols">
                            <div class="form-group">
                                <label for="nomeCargo">Nome do Cargo</label>
                                <input type="text" id="nomeCargo" name="nomeCargo" 
                                       value="<%= f.getCargo() != null ? f.getCargo().getNome() : "" %>" required>
                            </div>
                            <div class="form-group">
                                <label for="funcao">Função</label>
                                <input type="text" id="funcao" name="funcao" 
                                       value="<%= f.getCargo() != null ? f.getCargo().getFuncao() : "" %>" required>
                            </div>
                            <div class="form-group">
                                <label for="salarioBase">Salário Base (R$)</label>
                                <input type="number" step="0.01" id="salarioBase" name="salarioBase" 
                                       value="<%= f.getCargo() != null ? f.getCargo().getSalarioBase() : "" %>" required>
                            </div>
                        </div>
                        <div class="form-grid" style="margin-top: 18px;">
                            <div class="form-group">
                                <label for="dataDeAdmissao">Data de Admissão</label>
                                <input type="date" id="dataDeAdmissao" name="dataDeAdmissao" 
                                       value="<%= f.getDataDeAdmissao() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(f.getDataDeAdmissao()) : "" %>" required>
                            </div>
                        </div>
                    </div>

                    <!-- Endereço -->
                    <div class="form-section">
                        <div class="section-title">
                            <span class="section-icon orange">📍</span> Endereço
                        </div>
                        <div class="form-grid">
                            <div class="form-group full">
                                <label for="rua">Rua</label>
                                <input type="text" id="rua" name="rua" 
                                       value="<%= f.getEndereco() != null ? f.getEndereco().getRua() : "" %>" required>
                            </div>
                            <div class="form-group">
                                <label for="bairro">Bairro</label>
                                <input type="text" id="bairro" name="bairro" 
                                       value="<%= f.getEndereco() != null ? f.getEndereco().getBairro() : "" %>" required>
                            </div>
                            <div class="form-group">
                                <label for="cidade">Cidade</label>
                                <input type="text" id="cidade" name="cidade" 
                                       value="<%= f.getEndereco() != null ? f.getEndereco().getCidade() : "" %>" required>
                            </div>
                            <div class="form-group">
                                <label for="estado">Estado</label>
                                <select id="estado" name="estado" required>
                                    <option value="<%= f.getEndereco() != null ? f.getEndereco().getEstado() : "" %>" selected>
                                        <%= f.getEndereco() != null ? f.getEndereco().getEstado() : "Selecione" %>
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="cep">CEP</label>
                                <input type="number" id="cep" name="cep" 
                                       value="<%= f.getEndereco() != null ? f.getEndereco().getCep() : "" %>" required>
                            </div>
                        </div>
                    </div>

                <% } else { %>
                    <p style="color:red;">Erro: Nenhum funcionário carregado para edição.</p>
                <% } %>

                <div class="form-actions">
                    <a href="FuncionarioControllerConsultarTodos?op=CONSULTAR_TODOS_FUNCIONARIO" class="btn btn-outline">Voltar</a>
                    <button type="submit" class="btn btn-primary">Atualizar Funcionário</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>