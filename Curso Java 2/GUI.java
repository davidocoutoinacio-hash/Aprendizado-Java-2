import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {
    private static final String ARQUIVO_DADOS = "cadastros.dat";
    private List<Pessoa> cadastros = new ArrayList<>();
    
    // Componentes da interface
    private JTextField txtNome, txtCpf, txtEmail, txtTelefone, txtBusca;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JLabel lblStatus;
    
    // Cores modernas
    private final Color COR_PRIMARIA = new Color(45, 52, 54);
    private final Color COR_SECUNDARIA = new Color(99, 110, 114);
    private final Color COR_DESTAQUE = new Color(0, 184, 148);
    private final Color COR_PERIGO = new Color(255, 71, 87);
    private final Color COR_FUNDO = new Color(245, 246, 250);
    private final Color COR_BRANCO = Color.WHITE;

    public GUI() {
        carregarDados();
        configurarJanela();
        inicializarComponentes();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Sistema de Cadastro Moderno");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COR_FUNDO);
    }

    private void inicializarComponentes() {
        // Painel superior - Título
        add(criarPainelTitulo(), BorderLayout.NORTH);
        
        // Painel central - Formulário e Tabela
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(criarPainelFormulario());
        splitPane.setBottomComponent(criarPainelTabela());
        splitPane.setDividerLocation(280);
        splitPane.setBackground(COR_FUNDO);
        add(splitPane, BorderLayout.CENTER);
        
        // Painel inferior - Status
        add(criarPainelStatus(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PRIMARIA);
        painel.setPreferredSize(new Dimension(0, 70));
        painel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("📋 SISTEMA DE CADASTRO", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(COR_BRANCO);
        
        JLabel subtitulo = new JLabel("Gerencie seus cadastros de forma simples e eficiente", SwingConstants.CENTER);
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

        // Título do formulário
        JLabel lblTituloForm = new JLabel("📝 Novo Cadastro");
        lblTituloForm.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloForm.setForeground(COR_PRIMARIA);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        painel.add(lblTituloForm, gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        int linha = 1;

        // Nome Completo
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

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.setBackground(COR_BRANCO);
        
        JButton btnCadastrar = criarBotao("✅ Cadastrar", COR_DESTAQUE);
        btnCadastrar.addActionListener(e -> cadastrarPessoa());
        
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
        txtBusca.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                filtrarTabela(txtBusca.getText());
            }
        });
        txtBusca.addActionListener(e -> filtrarTabela(txtBusca.getText()));

        // Painel com botões
        JPanel painelBotoesBusca = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        painelBotoesBusca.setBackground(COR_BRANCO);
        
        JButton btnPesquisar = criarBotao("🔍 Pesquisar", COR_DESTAQUE);
        btnPesquisar.setPreferredSize(new Dimension(140, 35));
        btnPesquisar.addActionListener(e -> filtrarTabela(txtBusca.getText()));
        
        JButton btnLimparBusca = criarBotao("🔄 Limpar", COR_SECUNDARIA);
        btnLimparBusca.setPreferredSize(new Dimension(100, 35));
        btnLimparBusca.addActionListener(e -> {
            txtBusca.setText("");
            filtrarTabela("");
        });
        
        JButton btnExcluir = criarBotao("🗑️ Excluir", COR_PERIGO);
        btnExcluir.setPreferredSize(new Dimension(100, 35));
        btnExcluir.addActionListener(e -> excluirSelecionado());
        
        painelBotoesBusca.add(btnPesquisar);
        painelBotoesBusca.add(btnLimparBusca);
        painelBotoesBusca.add(btnExcluir);

        painelBusca.add(lblBusca, BorderLayout.WEST);
        painelBusca.add(txtBusca, BorderLayout.CENTER);
        painelBusca.add(painelBotoesBusca, BorderLayout.EAST);

        // Tabela
        String[] colunas = {"Nome", "CPF", "Email", "Telefone"};
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
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Ajustar largura das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(250); // Nome
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150); // CPF
        tabela.getColumnModel().getColumn(2).setPreferredWidth(250); // Email
        tabela.getColumnModel().getColumn(3).setPreferredWidth(150); // Telefone

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

        lblStatus = new JLabel("✅ Sistema pronto. Total de cadastros: " + cadastros.size());
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

    private void cadastrarPessoa() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        // Validações
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            mostrarErro("⚠️ Todos os campos são obrigatórios!");
            return;
        }

        if (cpfJaCadastrado(cpf)) {
            mostrarErro("❌ Este CPF já está cadastrado no sistema!");
            txtCpf.requestFocus();
            return;
        }

        if (emailJaCadastrado(email)) {
            mostrarErro("❌ Este email já está cadastrado no sistema!");
            txtEmail.requestFocus();
            return;
        }

        // Cadastrar
        Pessoa novaPessoa = new Pessoa(nome, cpf, email, telefone);
        cadastros.add(novaPessoa);
        salvarDados();
        atualizarTabela();
        limparCampos();
        
        atualizarStatus("✅ Cadastro realizado com sucesso! Total: " + cadastros.size(), COR_DESTAQUE);
    }

    private void excluirSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            mostrarErro("⚠️ Selecione um cadastro para excluir!");
            return;
        }

        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir o cadastro de CPF: " + cpf + "?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            cadastros.removeIf(p -> p.getCpf().equals(cpf));
            salvarDados();
            atualizarTabela();
            atualizarStatus("🗑️ Cadastro excluído com sucesso! Total: " + cadastros.size(), COR_PERIGO);
        }
    }

    private void filtrarTabela(String busca) {
        modeloTabela.setRowCount(0);
        String buscaLower = busca.toLowerCase();
        
        for (Pessoa p : cadastros) {
            if (busca.isEmpty() || 
                p.getNome().toLowerCase().contains(buscaLower) ||
                p.getCpf().contains(buscaLower) ||
                p.getEmail().toLowerCase().contains(buscaLower) ||
                p.getTelefone().contains(buscaLower)) {
                
                modeloTabela.addRow(new Object[]{
                    p.getNome(), p.getCpf(), p.getEmail(), p.getTelefone()
                });
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Pessoa p : cadastros) {
            modeloTabela.addRow(new Object[]{
                p.getNome(), p.getCpf(), p.getEmail(), p.getTelefone()
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

    private boolean cpfJaCadastrado(String cpf) {
        return cadastros.stream().anyMatch(p -> p.getCpf().equals(cpf));
    }

    private boolean emailJaCadastrado(String email) {
        return cadastros.stream().anyMatch(p -> p.getEmail().equalsIgnoreCase(email));
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void atualizarStatus(String mensagem, Color cor) {
        lblStatus.setText(mensagem);
        lblStatus.setForeground(COR_BRANCO);
        getContentPane().repaint();
        
        Timer timer = new Timer(3000, e -> {
            lblStatus.setText("✅ Sistema pronto. Total de cadastros: " + cadastros.size());
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(cadastros);
        } catch (IOException e) {
            mostrarErro("❌ Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
                cadastros = (List<Pessoa>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                cadastros = new ArrayList<>();
            }
        }
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
