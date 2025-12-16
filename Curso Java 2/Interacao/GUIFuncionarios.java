package Interacao;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUIFuncionarios extends JFrame {
    private GerenciadorFuncionarios gerenciador;
    
    // Componentes
    private JTextField txtNome, txtCpf, txtEmail, txtTelefone, txtBusca;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JLabel lblStatus;
    
    // Cores
    private final Color COR_PRIMARIA = new Color(45, 52, 54);
    private final Color COR_SECUNDARIA = new Color(99, 110, 114);
    private final Color COR_DESTAQUE = new Color(0, 184, 148);
    private final Color COR_PERIGO = new Color(255, 71, 87);
    private final Color COR_FUNDO = new Color(245, 246, 250);
    private final Color COR_BRANCO = Color.WHITE;
    
    public GUIFuncionarios() {
        gerenciador = new GerenciadorFuncionarios();
        configurarJanela();
        inicializarComponentes();
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("Sistema de Gestão de Funcionários");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COR_FUNDO);
    }
    
    private void inicializarComponentes() {
        add(criarPainelTitulo(), BorderLayout.NORTH);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(criarPainelFormulario());
        splitPane.setBottomComponent(criarPainelTabela());
        splitPane.setDividerLocation(280);
        splitPane.setBackground(COR_FUNDO);
        add(splitPane, BorderLayout.CENTER);
        
        add(criarPainelStatus(), BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PRIMARIA);
        painel.setPreferredSize(new Dimension(0, 70));
        painel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("👥 SISTEMA DE GESTÃO DE FUNCIONÁRIOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(COR_BRANCO);
        
        JLabel subtitulo = new JLabel("Gerencie funcionários com perfis completos e históricos detalhados", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(189, 195, 199));

        JPanel textos = new JPanel(new GridLayout(2, 1));
        textos.setBackground(COR_PRIMARIA);
        textos.add(titulo);
        textos.add(subtitulo);
        
        painel.add(textos, BorderLayout.CENTER);
        return painel;
    }
    
    private JPanel criarPainelFormulario() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        painel.setBackground(COR_BRANCO);
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 221, 225), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTituloForm = new JLabel("📝 Cadastro Inicial");
        lblTituloForm.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloForm.setForeground(COR_PRIMARIA);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        painel.add(lblTituloForm, gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        int linha = 1;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNome.setForeground(COR_PRIMARIA);
        painel.add(lblNome, gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        txtNome = criarTextField();
        painel.add(txtNome, gbc);
        linha++;
        
        // CPF
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCpf.setForeground(COR_PRIMARIA);
        painel.add(lblCpf, gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        txtCpf = criarTextField();
        painel.add(txtCpf, gbc);
        
        // Email
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEmail.setForeground(COR_PRIMARIA);
        painel.add(lblEmail, gbc);
        
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        txtEmail = criarTextField();
        painel.add(txtEmail, gbc);
        linha++;
        
        // Telefone
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTelefone.setForeground(COR_PRIMARIA);
        painel.add(lblTelefone, gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        txtTelefone = criarTextField();
        painel.add(txtTelefone, gbc);
        linha++;

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.setBackground(COR_BRANCO);
        
        JButton btnCadastrar = criarBotao("✅ Cadastrar", COR_DESTAQUE);
        btnCadastrar.addActionListener(e -> cadastrarFuncionario());
        
        JButton btnLimpar = criarBotao("🔄 Limpar", COR_SECUNDARIA);
        btnLimpar.addActionListener(e -> limparCampos());
        
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnCadastrar);

        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        painel.add(painelBotoes, gbc);

        painelPrincipal.add(painel, BorderLayout.CENTER);
        return painelPrincipal;
    }
    
    private JPanel criarPainelTabela() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(0, 15, 15, 15));

        // Painel de busca
        JPanel painelBusca = new JPanel(new BorderLayout(10, 0));
        painelBusca.setBackground(COR_BRANCO);
        painelBusca.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 221, 225), 1, true),
            new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblBusca = new JLabel("🔍 Buscar:");
        lblBusca.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBusca.setForeground(COR_PRIMARIA);
        
        txtBusca = criarTextField();
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarTabela(txtBusca.getText());
            }
        });

        JPanel painelBotoesBusca = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        painelBotoesBusca.setBackground(COR_BRANCO);
        
        JButton btnVerPerfil = criarBotao("👤 Ver Perfil", COR_DESTAQUE);
        btnVerPerfil.setPreferredSize(new Dimension(130, 35));
        btnVerPerfil.addActionListener(e -> abrirPerfil());
        
        JButton btnExcluir = criarBotao("🗑️ Excluir", COR_PERIGO);
        btnExcluir.setPreferredSize(new Dimension(100, 35));
        btnExcluir.addActionListener(e -> excluirSelecionado());
        
        painelBotoesBusca.add(btnVerPerfil);
        painelBotoesBusca.add(btnExcluir);

        painelBusca.add(lblBusca, BorderLayout.WEST);
        painelBusca.add(txtBusca, BorderLayout.CENTER);
        painelBusca.add(painelBotoesBusca, BorderLayout.EAST);

        // Tabela
        String[] colunas = {"Nome", "CPF", "Email", "Cargo", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setRowHeight(30);
        tabela.setSelectionBackground(new Color(0, 184, 148, 50));
        tabela.setSelectionForeground(COR_PRIMARIA);
        tabela.setGridColor(new Color(220, 221, 225));
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(COR_SECUNDARIA);
        tabela.getTableHeader().setForeground(COR_BRANCO);
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 35));
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(new LineBorder(new Color(220, 221, 225), 1));

        painelPrincipal.add(painelBusca, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        atualizarTabela();
        return painelPrincipal;
    }
    
    private JPanel criarPainelStatus() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_PRIMARIA);
        painel.setPreferredSize(new Dimension(0, 35));
        painel.setBorder(new EmptyBorder(5, 15, 5, 15));

        lblStatus = new JLabel("✅ Sistema pronto. Total de funcionários: " + gerenciador.listarTodos().size());
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStatus.setForeground(COR_BRANCO);
        
        painel.add(lblStatus, BorderLayout.WEST);
        return painel;
    }
    
    private JTextField criarTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 221, 225), 1, true),
            new EmptyBorder(5, 10, 5, 10)
        ));
        return txt;
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(cor);
        btn.setForeground(COR_BRANCO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(180, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private void cadastrarFuncionario() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (gerenciador.cpfJaCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this, "❌ Este CPF já está cadastrado no sistema!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (gerenciador.emailJaCadastrado(email)) {
            JOptionPane.showMessageDialog(this, "❌ Este email já está cadastrado no sistema!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Funcionario funcionario = new Funcionario(nome, cpf, email, telefone);
        gerenciador.adicionarFuncionario(funcionario);
        atualizarTabela();
        limparCampos();
        
        lblStatus.setText("✅ Cadastro realizado com sucesso! Total: " + gerenciador.listarTodos().size());
    }
    
    private void abrirPerfil() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "⚠️ Selecione um funcionário para ver o perfil!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        Funcionario funcionario = gerenciador.buscarPorCpf(cpf);
        
        if (funcionario != null) {
            new PerfilFuncionario(funcionario, gerenciador);
        }
    }
    
    private void excluirSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "⚠️ Selecione um funcionário para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir este funcionário?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            gerenciador.removerFuncionario(cpf);
            atualizarTabela();
            lblStatus.setText("🗑️ Funcionário excluído! Total: " + gerenciador.listarTodos().size());
        }
    }
    
    private void filtrarTabela(String busca) {
        modeloTabela.setRowCount(0);
        String buscaLower = busca.toLowerCase();
        
        for (Funcionario f : gerenciador.listarTodos()) {
            if (busca.isEmpty() || 
                f.getNome().toLowerCase().contains(buscaLower) ||
                f.getCpf().contains(buscaLower) ||
                f.getEmail().toLowerCase().contains(buscaLower) ||
                (f.getCargo() != null && f.getCargo().toLowerCase().contains(buscaLower))) {
                
                modeloTabela.addRow(new Object[]{
                    f.getNome(), 
                    f.getCpf(), 
                    f.getEmail(), 
                    f.getCargo() != null ? f.getCargo() : "Não definido",
                    f.getStatus()
                });
            }
        }
    }
    
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Funcionario f : gerenciador.listarTodos()) {
            modeloTabela.addRow(new Object[]{
                f.getNome(), 
                f.getCpf(), 
                f.getEmail(), 
                f.getCargo() != null ? f.getCargo() : "Não definido",
                f.getStatus()
            });
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtNome.requestFocus();
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new GUIFuncionarios());
    }
}
