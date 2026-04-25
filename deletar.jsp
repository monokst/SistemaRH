<%-- 
    deletar.jsp 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deletar Funcionário - ekko RH</title>
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

    <!-- Main -->
    <main class="main">
        <h1 class="page-title">Deletar Funcionário</h1>
        <p class="page-subtitle">Digite o ID para remover o funcionário do sistema</p>

        <div class="form-card">
            <form action="FuncionarioControllerDeletar" method="post">

                <div class="form-group">
                    <label for="id_fun">ID do Funcionário</label>
                    <input type="text" id="id_fun" name="id_fun" placeholder="Ex: 1" required>
                </div>

                <div class="form-actions">
                    <a href="homepage.html" class="btn btn-outline">Voltar</a>
                    
                    <button type="submit" name="op" value="DELETAR_FUNCIONARIO" 
                            class="btn btn-primary"
                            onclick="return confirm('Tem certeza que deseja deletar este funcionário?')">
                        Deletar Funcionário
                    </button>
                </div>

               <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <div style="background: #d4edda;
                 color: #155724;
                 padding: 15px 20px;
                 border-radius: 8px;
                 margin-bottom: 25px;
                 font-weight: 500;">
                <%= msg%>
            </div>
            <% }%>

            </form>
        </div>
    </main>

</body>
</html>