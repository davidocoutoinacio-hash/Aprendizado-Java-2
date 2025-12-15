package ALGORITIMO.dio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GUI extends JFrame {
    
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Componentes do formulário de cadastro
    private JTextField txtCpfCad, txtNomeCad, txtTelCad, txtEmailCad;
    
    // Componentes de busca/edição
    private JTextField txtBusca;
    private JComboBox<String> cbTipoBusca;
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    
    // Cores modernas
    private final Color COR_PRIMARY = new Color(33, 150, 243);
    private final Color COR_SECONDARY = new Color(103, 58, 183);
    private final Color COR_SUCCESS = new Color(76, 175, 80);
    private final Color COR_DANGER = new Color(244, 67, 54);
    private final Color COR_BG = new Color(250, 250, 250);
    private final Color COR_CARD = Color.WHITE;
    
    public GUI() {
        setTitle("Sistema de Cadastro de Usuários");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Configurar layout principal
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(COR_BG);
        
        // Criar telas
        criarTelaMenu();
        criarTelaCadastro();
        criarTelaConsulta();
        criarTelaEdicao();
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void criarTelaMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(COR_BG);
        
        // Header
        JPanel header = criarHeader("SISTEMA DE CADASTRO");
        menuPanel.add(header, BorderLayout.NORTH);
        
        // Centro com botões
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(COR_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Card com botões
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(4, 1, 20, 20));
        cardPanel.setBackground(COR_CARD);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        
        JButton btnCadastrar = criarBotaoGrande("📝 CADASTRAR USUÁRIO", COR_PRIMARY);
        btnCadastrar.addActionListener(e -> cardLayout.show(mainPanel, "cadastro"));
        
        JButton btnEditar = criarBotaoGrande("✏️ EDITAR USUÁRIO", COR_SECONDARY);
        btnEditar.addActionListener(e -> cardLayout.show(mainPanel, "edicao"));
        
        JButton btnConsultar = criarBotaoGrande("📋 CONSULTAR USUÁRIOS", COR_SUCCESS);
        btnConsultar.addActionListener(e -> {
            atualizarTabelaUsuarios();
            cardLayout.show(mainPanel, "consulta");
        });
        
        JButton btnSair = criarBotaoGrande("🚪 SAIR", COR_DANGER);
        btnSair.addActionListener(e -> System.exit(0));
        
        cardPanel.add(btnCadastrar);
        cardPanel.add(btnEditar);
        cardPanel.add(btnConsultar);
        cardPanel.add(btnSair);
        
        centerPanel.add(cardPanel, gbc);
        menuPanel.add(centerPanel, BorderLayout.CENTER);
        
        mainPanel.add(menuPanel, "menu");
    }
    
    private void criarTelaCadastro() {
        JPanel cadastroPanel = new JPanel(new BorderLayout());
        cadastroPanel.setBackground(COR_BG);
        
        // Header
        cadastroPanel.add(criarHeader("CADASTRAR NOVO USUÁRIO"), BorderLayout.NORTH);
        
        // Formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(COR_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel cardForm = new JPanel(new GridBagLayout());
        cardForm.setBackground(COR_CARD);
        cardForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        
        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.insets = new Insets(10, 10, 10, 10);
        gbcForm.fill = GridBagConstraints.HORIZONTAL;
        gbcForm.gridx = 0;
        
        // CPF
        gbcForm.gridy = 0;
        cardForm.add(criarLabel("CPF (somente números):"), gbcForm);
        gbcForm.gridy = 1;
        txtCpfCad = criarTextField();
        cardForm.add(txtCpfCad, gbcForm);
        
        // Nome
        gbcForm.gridy = 2;
        cardForm.add(criarLabel("Nome Completo:"), gbcForm);
        gbcForm.gridy = 3;
        txtNomeCad = criarTextField();
        cardForm.add(txtNomeCad, gbcForm);
        
        // Telefone
        gbcForm.gridy = 4;
        cardForm.add(criarLabel("Telefone:"), gbcForm);
        gbcForm.gridy = 5;
        txtTelCad = criarTextField();
        cardForm.add(txtTelCad, gbcForm);
        
        // Email
        gbcForm.gridy = 6;
        cardForm.add(criarLabel("Email:"), gbcForm);
        gbcForm.gridy = 7;
        txtEmailCad = criarTextField();
        cardForm.add(txtEmailCad, gbcForm);
        
        // Botões
        gbcForm.gridy = 8;
        gbcForm.insets = new Insets(30, 10, 10, 10);
        JPanel botoesPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        botoesPanel.setOpaque(false);
        
        JButton btnSalvar = criarBotao("💾 SALVAR", COR_SUCCESS);
        btnSalvar.addActionListener(e -> salvarUsuario());
        
        JButton btnVoltar = criarBotao("⬅️ VOLTAR", Color.GRAY);
        btnVoltar.addActionListener(e -> {
            limparFormulario();
            cardLayout.show(mainPanel, "menu");
        });
        
        botoesPanel.add(btnSalvar);
        botoesPanel.add(btnVoltar);
        cardForm.add(botoesPanel, gbcForm);
        
        formPanel.add(cardForm, gbc);
        cadastroPanel.add(formPanel, BorderLayout.CENTER);
        
        mainPanel.add(cadastroPanel, "cadastro");
    }
    
    private void criarTelaConsulta() {
        JPanel consultaPanel = new JPanel(new BorderLayout());
        consultaPanel.setBackground(COR_BG);
        
        // Header
        consultaPanel.add(criarHeader("USUÁRIOS CADASTRADOS"), BorderLayout.NORTH);
        
        // Tabela
        String[] colunas = {"CPF", "Nome", "Telefone", "Email"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setRowHeight(40);
        tabelaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaUsuarios.setSelectionBackground(COR_PRIMARY.brighter());
        tabelaUsuarios.setSelectionForeground(Color.WHITE);
        tabelaUsuarios.setGridColor(new Color(240, 240, 240));
        
        // Header da tabela
        JTableHeader tableHeader = tabelaUsuarios.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(COR_PRIMARY);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 45));
        
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        consultaPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Botão voltar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(COR_BG);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));
        
        JButton btnVoltar = criarBotao("⬅️ VOLTAR AO MENU", Color.GRAY);
        btnVoltar.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        bottomPanel.add(btnVoltar);
        
        consultaPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainPanel.add(consultaPanel, "consulta");
    }
    
    private void criarTelaEdicao() {
        JPanel edicaoPanel = new JPanel(new BorderLayout());
        edicaoPanel.setBackground(COR_BG);
        
        // Header
        edicaoPanel.add(criarHeader("EDITAR USUÁRIO"), BorderLayout.NORTH);
        
        // Painel de busca
        JPanel buscaPanel = new JPanel();
        buscaPanel.setLayout(new GridBagLayout());
        buscaPanel.setBackground(COR_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel cardBusca = new JPanel(new GridBagLayout());
        cardBusca.setBackground(COR_CARD);
        cardBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        
        GridBagConstraints gbcBusca = new GridBagConstraints();
        gbcBusca.insets = new Insets(10, 10, 10, 10);
        gbcBusca.fill = GridBagConstraints.HORIZONTAL;
        
        // ComboBox tipo de busca
        gbcBusca.gridx = 0;
        gbcBusca.gridy = 0;
        cardBusca.add(criarLabel("Buscar por:"), gbcBusca);
        
        gbcBusca.gridy = 1;
        String[] tiposBusca = {"CPF", "Nome", "Telefone", "Email"};
        cbTipoBusca = new JComboBox<>(tiposBusca);
        cbTipoBusca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbTipoBusca.setPreferredSize(new Dimension(400, 40));
        cardBusca.add(cbTipoBusca, gbcBusca);
        
        // Campo de busca
        gbcBusca.gridy = 2;
        cardBusca.add(criarLabel("Digite o termo de busca:"), gbcBusca);
        
        gbcBusca.gridy = 3;
        txtBusca = criarTextField();
        cardBusca.add(txtBusca, gbcBusca);
        
        // Botões
        gbcBusca.gridy = 4;
        gbcBusca.insets = new Insets(30, 10, 10, 10);
        JPanel botoesBuscaPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        botoesBuscaPanel.setOpaque(false);
        
        JButton btnBuscar = criarBotao("🔍 BUSCAR", COR_PRIMARY);
        btnBuscar.addActionListener(e -> buscarParaEditar());
        
        JButton btnVoltar = criarBotao("⬅️ VOLTAR", Color.GRAY);
        btnVoltar.addActionListener(e -> {
            txtBusca.setText("");
            cardLayout.show(mainPanel, "menu");
        });
        
        botoesBuscaPanel.add(btnBuscar);
        botoesBuscaPanel.add(btnVoltar);
        cardBusca.add(botoesBuscaPanel, gbcBusca);
        
        buscaPanel.add(cardBusca, gbc);
        edicaoPanel.add(buscaPanel, BorderLayout.CENTER);
        
        mainPanel.add(edicaoPanel, "edicao");
    }
    
    private JPanel criarHeader(String titulo) {
        JPanel header = new JPanel();
        header.setBackground(COR_PRIMARY);
        header.setPreferredSize(new Dimension(getWidth(), 80));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        header.add(lblTitulo);
        
        return header;
    }
    
    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }
    
    private JTextField criarTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(400, 40));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return textField;
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(200, 45));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(cor.darker());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(cor);
            }
        });
        
        return btn;
    }
    
    private JButton criarBotaoGrande(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setPreferredSize(new Dimension(400, 70));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(cor.darker());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(cor);
            }
        });
        
        return btn;
    }
    
    private void salvarUsuario() {
        String cpf = txtCpfCad.getText().trim();
        String nome = txtNomeCad.getText().trim();
        String telefone = txtTelCad.getText().trim();
        String email = txtEmailCad.getText().trim();
        
        if (cpf.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "CPF e Nome são obrigatórios!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Usuario usuario = new Usuario(cpf, nome, telefone, email);
        if (usuario.salvar()) {
            JOptionPane.showMessageDialog(this, 
                "✓ Usuário cadastrado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            limparFormulario();
            cardLayout.show(mainPanel, "menu");
        } else {
            JOptionPane.showMessageDialog(this, 
                "✗ CPF já cadastrado!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void atualizarTabelaUsuarios() {
        modeloTabela.setRowCount(0);
        List<Usuario> usuarios = Usuario.listarTodos();
        
        for (Usuario u : usuarios) {
            modeloTabela.addRow(new Object[]{
                u.getCpf(),
                u.getNome(),
                u.getTelefone(),
                u.getEmail()
            });
        }
    }
    
    private void buscarParaEditar() {
        String termo = txtBusca.getText().trim();
        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Digite um termo para buscar!", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String tipoBusca = (String) cbTipoBusca.getSelectedItem();
        List<Usuario> usuarios = null;
        
        switch (tipoBusca) {
            case "CPF":
                Usuario u = Usuario.buscarPorCpf(termo);
                if (u != null) {
                    abrirDialogEdicao(u);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Usuário não encontrado!", 
                        "Aviso", 
                        JOptionPane.WARNING_MESSAGE);
                }
                return;
            case "Nome":
                usuarios = Usuario.buscarPorNome(termo);
                break;
            case "Telefone":
                usuarios = Usuario.buscarPorTelefone(termo);
                break;
            case "Email":
                usuarios = Usuario.buscarPorEmail(termo);
                break;
        }
        
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nenhum usuário encontrado!", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
        } else if (usuarios.size() == 1) {
            abrirDialogEdicao(usuarios.get(0));
        } else {
            selecionarUsuarioDaLista(usuarios);
        }
    }
    
    private void selecionarUsuarioDaLista(List<Usuario> usuarios) {
        String[] opcoes = new String[usuarios.size()];
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            opcoes[i] = u.getNome() + " - CPF: " + u.getCpf();
        }
        
        String selecao = (String) JOptionPane.showInputDialog(
            this,
            "Vários usuários encontrados. Selecione um:",
            "Selecionar Usuário",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );
        
        if (selecao != null) {
            for (Usuario u : usuarios) {
                if (selecao.contains(u.getCpf())) {
                    abrirDialogEdicao(u);
                    break;
                }
            }
        }
    }
    
    private void abrirDialogEdicao(Usuario usuario) {
        JDialog dialog = new JDialog(this, "Editar Usuário", true);
        dialog.setSize(500, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        // CPF (somente leitura)
        gbc.gridy = 0;
        formPanel.add(criarLabel("CPF:"), gbc);
        gbc.gridy = 1;
        JTextField txtCpf = criarTextField();
        txtCpf.setText(usuario.getCpf());
        txtCpf.setEditable(false);
        txtCpf.setBackground(new Color(240, 240, 240));
        formPanel.add(txtCpf, gbc);
        
        // Nome
        gbc.gridy = 2;
        formPanel.add(criarLabel("Nome:"), gbc);
        gbc.gridy = 3;
        JTextField txtNome = criarTextField();
        txtNome.setText(usuario.getNome());
        formPanel.add(txtNome, gbc);
        
        // Telefone
        gbc.gridy = 4;
        formPanel.add(criarLabel("Telefone:"), gbc);
        gbc.gridy = 5;
        JTextField txtTel = criarTextField();
        txtTel.setText(usuario.getTelefone());
        formPanel.add(txtTel, gbc);
        
        // Email
        gbc.gridy = 6;
        formPanel.add(criarLabel("Email:"), gbc);
        gbc.gridy = 7;
        JTextField txtEmail = criarTextField();
        txtEmail.setText(usuario.getEmail());
        formPanel.add(txtEmail, gbc);
        
        // Botões
        gbc.gridy = 8;
        gbc.insets = new Insets(30, 10, 10, 10);
        JPanel botoesPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        botoesPanel.setOpaque(false);
        
        JButton btnSalvar = criarBotao("💾 SALVAR", COR_SUCCESS);
        btnSalvar.addActionListener(e -> {
            usuario.setNome(txtNome.getText().trim());
            usuario.setTelefone(txtTel.getText().trim());
            usuario.setEmail(txtEmail.getText().trim());
            
            if (usuario.atualizar()) {
                JOptionPane.showMessageDialog(dialog, 
                    "✓ Usuário atualizado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                txtBusca.setText("");
            }
        });
        
        JButton btnCancelar = criarBotao("❌ CANCELAR", Color.GRAY);
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        botoesPanel.add(btnSalvar);
        botoesPanel.add(btnCancelar);
        formPanel.add(botoesPanel, gbc);
        
        dialog.add(formPanel);
        dialog.setVisible(true);
    }
    
    private void limparFormulario() {
        txtCpfCad.setText("");
        txtNomeCad.setText("");
        txtTelCad.setText("");
        txtEmailCad.setText("");
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
