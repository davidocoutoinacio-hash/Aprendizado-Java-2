package Interacao;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class HubFuncionarios extends JFrame {
    private GerenciadorFuncionarios gerenciador;
    
    // Componentes
    private JTextField txtBuscaRapida;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JPanel painelCards;
    private JLabel lblTotalFuncionarios, lblAtivos, lblInativos, lblAdvertencias;
    
    // Cores tema escuro moderno
    private final Color COR_FUNDO_ESCURO = new Color(23, 32, 42);
    private final Color COR_CARD = new Color(32, 44, 57);
    private final Color COR_CARD_HOVER = new Color(41, 55, 72);
    private final Color COR_DESTAQUE = new Color(52, 211, 153);
    private final Color COR_INFO = new Color(96, 165, 250);
    private final Color COR_AVISO = new Color(251, 191, 36);
    private final Color COR_PERIGO = new Color(248, 113, 113);
    private final Color COR_TEXTO_PRINCIPAL = new Color(241, 245, 249);
    private final Color COR_TEXTO_SECUNDARIO = new Color(148, 163, 184);
    private final Color COR_BORDA = new Color(51, 65, 85);
    
    public HubFuncionarios() {
        gerenciador = new GerenciadorFuncionarios();
        configurarJanela();
        inicializarComponentes();
        atualizarDashboard();
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("Hub de Gestão de Funcionários");
        setSize(1400, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(COR_FUNDO_ESCURO);
    }
    
    private void inicializarComponentes() {
        // Sidebar
        add(criarSidebar(), BorderLayout.WEST);
        
        // Conteúdo principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(COR_FUNDO_ESCURO);
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header com busca e estatísticas
        painelPrincipal.add(criarHeader(), BorderLayout.NORTH);
        
        // Conteúdo central com tabela
        painelPrincipal.add(criarConteudoCentral(), BorderLayout.CENTER);
        
        add(painelPrincipal, BorderLayout.CENTER);
    }
    
    private JPanel criarSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(COR_CARD);
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setBorder(new EmptyBorder(30, 20, 30, 20));
        
        // Logo e título
        JPanel painelLogo = new JPanel();
        painelLogo.setLayout(new BoxLayout(painelLogo, BoxLayout.Y_AXIS));
        painelLogo.setBackground(COR_CARD);
        painelLogo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblIcon = new JLabel("👥");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        lblIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblTitulo = new JLabel("HUB RH");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(COR_DESTAQUE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblSubtitulo = new JLabel("Sistema de Gestão");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(COR_TEXTO_SECUNDARIO);
        lblSubtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painelLogo.add(lblIcon);
        painelLogo.add(Box.createVerticalStrut(10));
        painelLogo.add(lblTitulo);
        painelLogo.add(lblSubtitulo);
        
        sidebar.add(painelLogo);
        sidebar.add(Box.createVerticalStrut(40));
        
        // Menu de opções
        sidebar.add(criarBotaoMenu("➕ Novo Funcionário", COR_DESTAQUE, e -> abrirNovoCadastro()));
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(criarBotaoMenu("📊 Dashboard", COR_INFO, e -> abrirDashboardCompleto()));
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(criarBotaoMenu("📁 Relatórios", COR_AVISO, e -> gerarRelatorio()));
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(criarBotaoMenu("⚙️ Configurações", COR_TEXTO_SECUNDARIO, e -> abrirConfiguracoes()));
        
        sidebar.add(Box.createVerticalGlue());
        
        // Informações do sistema
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setBackground(new Color(15, 23, 30));
        painelInfo.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        painelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblVersao = new JLabel("Versão 2.0");
        lblVersao.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblVersao.setForeground(COR_TEXTO_SECUNDARIO);
        
        JLabel lblAno = new JLabel("© 2025 Sistema RH");
        lblAno.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblAno.setForeground(COR_TEXTO_SECUNDARIO);
        
        painelInfo.add(lblVersao);
        painelInfo.add(lblAno);
        sidebar.add(painelInfo);
        
        return sidebar;
    }
    
    private JButton criarBotaoMenu(String texto, Color cor, java.awt.event.ActionListener action) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(COR_TEXTO_PRINCIPAL);
        btn.setBackground(COR_CARD);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(COR_CARD_HOVER);
                btn.setForeground(cor);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(COR_CARD);
                btn.setForeground(COR_TEXTO_PRINCIPAL);
            }
        });
        
        btn.addActionListener(action);
        return btn;
    }
    
    private JPanel criarHeader() {
        JPanel painel = new JPanel(new BorderLayout(15, 15));
        painel.setBackground(COR_FUNDO_ESCURO);
        
        // Busca rápida
        JPanel painelBusca = new JPanel(new BorderLayout(10, 0));
        painelBusca.setBackground(COR_CARD);
        painelBusca.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));
        
        JLabel lblIconeBusca = new JLabel("🔍");
        lblIconeBusca.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        
        txtBuscaRapida = new JTextField();
        txtBuscaRapida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBuscaRapida.setForeground(COR_TEXTO_PRINCIPAL);
        txtBuscaRapida.setBackground(COR_CARD);
        txtBuscaRapida.setBorder(null);
        txtBuscaRapida.setCaretColor(COR_DESTAQUE);
        txtBuscaRapida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarTabela(txtBuscaRapida.getText());
            }
        });
        
        JLabel lblPlaceholder = new JLabel("Buscar funcionário por nome, CPF, cargo...");
        lblPlaceholder.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPlaceholder.setForeground(COR_TEXTO_SECUNDARIO);
        
        painelBusca.add(lblIconeBusca, BorderLayout.WEST);
        painelBusca.add(txtBuscaRapida.getText().isEmpty() ? lblPlaceholder : txtBuscaRapida, BorderLayout.CENTER);
        painelBusca.add(txtBuscaRapida, BorderLayout.CENTER);
        
        // Cards de estatísticas
        painelCards = new JPanel(new GridLayout(1, 4, 15, 0));
        painelCards.setBackground(COR_FUNDO_ESCURO);
        
        lblTotalFuncionarios = new JLabel("0");
        lblAtivos = new JLabel("0");
        lblInativos = new JLabel("0");
        lblAdvertencias = new JLabel("0");
        
        painelCards.add(criarCardEstatistica("👥 Total de Funcionários", lblTotalFuncionarios, COR_INFO));
        painelCards.add(criarCardEstatistica("✅ Funcionários Ativos", lblAtivos, COR_DESTAQUE));
        painelCards.add(criarCardEstatistica("⏸️ Inativos/Afastados", lblInativos, COR_AVISO));
        painelCards.add(criarCardEstatistica("⚠️ Total de Advertências", lblAdvertencias, COR_PERIGO));
        
        painel.add(painelBusca, BorderLayout.NORTH);
        painel.add(painelCards, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarCardEstatistica(String titulo, JLabel lblValor, Color cor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(COR_TEXTO_SECUNDARIO);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblValor.setForeground(cor);
        lblValor.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);
        
        return card;
    }
    
    private JPanel criarConteudoCentral() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(COR_FUNDO_ESCURO);
        
        // Título da seção
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(COR_FUNDO_ESCURO);
        
        JLabel lblTitulo = new JLabel("📋 Lista de Funcionários");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(COR_TEXTO_PRINCIPAL);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setBackground(COR_FUNDO_ESCURO);
        
        JButton btnVerPerfil = criarBotaoAcao("👤 Ver Perfil Completo", COR_DESTAQUE);
        btnVerPerfil.addActionListener(e -> abrirPerfilSelecionado());
        
        JButton btnEditarRapido = criarBotaoAcao("✏️ Edição Rápida", COR_INFO);
        btnEditarRapido.addActionListener(e -> abrirEdicaoRapida());
        
        JButton btnExcluir = criarBotaoAcao("🗑️ Excluir", COR_PERIGO);
        btnExcluir.addActionListener(e -> excluirSelecionado());
        
        painelBotoes.add(btnVerPerfil);
        painelBotoes.add(btnEditarRapido);
        painelBotoes.add(btnExcluir);
        
        painelTitulo.add(lblTitulo, BorderLayout.WEST);
        painelTitulo.add(painelBotoes, BorderLayout.EAST);
        
        // Tabela
        String[] colunas = {"Nome", "CPF", "Cargo", "Departamento", "Salário", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setRowHeight(45);
        tabela.setBackground(COR_CARD);
        tabela.setForeground(COR_TEXTO_PRINCIPAL);
        tabela.setSelectionBackground(new Color(52, 211, 153, 30));
        tabela.setSelectionForeground(COR_DESTAQUE);
        tabela.setGridColor(COR_BORDA);
        tabela.setShowVerticalLines(true);
        tabela.setShowHorizontalLines(true);
        tabela.setIntercellSpacing(new Dimension(0, 1));
        
        // Header da tabela
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(new Color(15, 23, 30));
        tabela.getTableHeader().setForeground(COR_TEXTO_PRINCIPAL);
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 45));
        tabela.getTableHeader().setBorder(new LineBorder(COR_BORDA, 1));
        
        // Larguras das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        // Renderer customizado para status
        DefaultTableCellRenderer statusRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (column == 5 && value != null) {
                    String status = value.toString();
                    setHorizontalAlignment(SwingConstants.CENTER);
                    setFont(new Font("Segoe UI", Font.BOLD, 11));
                    
                    if (status.equals("Ativo")) {
                        setForeground(COR_DESTAQUE);
                        setText("● ATIVO");
                    } else if (status.equals("Férias")) {
                        setForeground(COR_INFO);
                        setText("● FÉRIAS");
                    } else if (status.equals("Afastado")) {
                        setForeground(COR_AVISO);
                        setText("● AFASTADO");
                    } else {
                        setForeground(COR_PERIGO);
                        setText("● " + status.toUpperCase());
                    }
                }
                
                if (!isSelected) {
                    setBackground(COR_CARD);
                }
                
                return c;
            }
        };
        tabela.getColumnModel().getColumn(5).setCellRenderer(statusRenderer);
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(COR_CARD);
        scrollPane.setBorder(new LineBorder(COR_BORDA, 1));
        scrollPane.getViewport().setBackground(COR_CARD);
        
        painel.add(painelTitulo, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        atualizarTabela();
        return painel;
    }
    
    private JButton criarBotaoAcao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(cor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(180, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(cor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(cor);
            }
        });
        
        return btn;
    }
    
    private void abrirNovoCadastro() {
        new DialogCadastroCompleto(this, null, gerenciador);
        atualizarDashboard();
    }
    
    private void abrirPerfilSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            mostrarMensagem("⚠️ Selecione um funcionário", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        Funcionario funcionario = gerenciador.buscarPorCpf(cpf);
        
        if (funcionario != null) {
            new PerfilFuncionario(funcionario, gerenciador);
            atualizarDashboard();
        }
    }
    
    private void abrirEdicaoRapida() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            mostrarMensagem("⚠️ Selecione um funcionário", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        Funcionario funcionario = gerenciador.buscarPorCpf(cpf);
        
        if (funcionario != null) {
            new DialogCadastroCompleto(this, funcionario, gerenciador);
            atualizarDashboard();
        }
    }
    
    private void excluirSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            mostrarMensagem("⚠️ Selecione um funcionário", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir este funcionário?\nEsta ação não pode ser desfeita.",
            "⚠️ Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            gerenciador.removerFuncionario(cpf);
            atualizarDashboard();
            mostrarMensagem("✅ Funcionário excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("📊 RELATÓRIO GERAL DO SISTEMA\n\n");
        relatorio.append("Total de Funcionários: ").append(gerenciador.listarTodos().size()).append("\n");
        
        int ativos = 0, inativos = 0;
        double totalSalarios = 0;
        
        for (Funcionario f : gerenciador.listarTodos()) {
            if (f.getStatus().equals("Ativo")) ativos++;
            else inativos++;
            totalSalarios += f.getSalarioAtual();
        }
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        
        relatorio.append("Ativos: ").append(ativos).append("\n");
        relatorio.append("Inativos: ").append(inativos).append("\n");
        relatorio.append("Folha Salarial Total: ").append(currencyFormat.format(totalSalarios)).append("\n");
        
        JTextArea txtRelatorio = new JTextArea(relatorio.toString());
        txtRelatorio.setEditable(false);
        txtRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JOptionPane.showMessageDialog(this, new JScrollPane(txtRelatorio), 
            "Relatório do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void abrirConfiguracoes() {
        Configuracoes config = Configuracoes.carregar();
        new DialogConfiguracoes(this, config, this);
    }
    
    private void abrirDashboardCompleto() {
        JFrame frameDashboard = new JFrame("📊 Dashboard - Análise Completa");
        frameDashboard.setSize(1200, 800);
        frameDashboard.setLocationRelativeTo(this);
        
        Configuracoes config = Configuracoes.carregar();
        PainelDashboard dashboard = new PainelDashboard(gerenciador, config);
        
        frameDashboard.add(dashboard);
        frameDashboard.setVisible(true);
    }
    
    private void filtrarTabela(String busca) {
        modeloTabela.setRowCount(0);
        String buscaLower = busca.toLowerCase();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        
        for (Funcionario f : gerenciador.listarTodos()) {
            if (busca.isEmpty() || 
                f.getNome().toLowerCase().contains(buscaLower) ||
                f.getCpf().contains(buscaLower) ||
                (f.getCargo() != null && f.getCargo().toLowerCase().contains(buscaLower)) ||
                (f.getDepartamento() != null && f.getDepartamento().toLowerCase().contains(buscaLower))) {
                
                modeloTabela.addRow(new Object[]{
                    f.getNome(),
                    f.getCpf(),
                    f.getCargo() != null ? f.getCargo() : "Não definido",
                    f.getDepartamento() != null ? f.getDepartamento() : "Não definido",
                    f.getSalarioAtual() > 0 ? currencyFormat.format(f.getSalarioAtual()) : "Não definido",
                    f.getStatus()
                });
            }
        }
    }
    
    private void atualizarTabela() {
        filtrarTabela("");
    }
    
    private void atualizarDashboard() {
        atualizarTabela();
        
        int total = gerenciador.listarTodos().size();
        int ativos = 0;
        int inativos = 0;
        int totalAdvertencias = 0;
        
        for (Funcionario f : gerenciador.listarTodos()) {
            if (f.getStatus().equals("Ativo")) {
                ativos++;
            } else {
                inativos++;
            }
            totalAdvertencias += f.getAdvertencias().size();
        }
        
        lblTotalFuncionarios.setText(String.valueOf(total));
        lblAtivos.setText(String.valueOf(ativos));
        lblInativos.setText(String.valueOf(inativos));
        lblAdvertencias.setText(String.valueOf(totalAdvertencias));
    }
    
    private void mostrarMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new HubFuncionarios());
    }
}
