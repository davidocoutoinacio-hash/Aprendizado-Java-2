package Interacao;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogCadastroCompleto extends JDialog {
    private Funcionario funcionario;
    private GerenciadorFuncionarios gerenciador;
    private boolean edicao;
    private String caminhoFotoSelecionada;
    
    // Componentes - Dados Básicos
    private JTextField txtNome, txtCpf, txtEmail, txtTelefone;
    
    // Componentes - Dados Pessoais
    private JTextField txtRg, txtDataNascimento, txtEndereco;
    private JLabel lblFotoPreview;
    
    // Componentes - Dados Profissionais
    private JTextField txtCargo, txtDepartamento, txtSalario;
    private JTextArea txtDescricaoCargo;
    private JComboBox<String> cmbStatus;
    
    // Cores
    private final Color COR_FUNDO = new Color(23, 32, 42);
    private final Color COR_CARD = new Color(32, 44, 57);
    private final Color COR_DESTAQUE = new Color(52, 211, 153);
    private final Color COR_INFO = new Color(96, 165, 250);
    private final Color COR_TEXTO_PRINCIPAL = new Color(241, 245, 249);
    private final Color COR_TEXTO_SECUNDARIO = new Color(148, 163, 184);
    private final Color COR_BORDA = new Color(51, 65, 85);
    
    public DialogCadastroCompleto(Frame parent, Funcionario funcionario, GerenciadorFuncionarios gerenciador) {
        super(parent, funcionario == null ? "Novo Funcionário" : "Editar Funcionário", true);
        this.funcionario = funcionario;
        this.gerenciador = gerenciador;
        this.edicao = (funcionario != null);
        
        configurarJanela();
        inicializarComponentes();
        
        if (edicao) {
            preencherDados();
        }
        
        setVisible(true);
    }
    
    private void configurarJanela() {
        setSize(900, 750);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        getContentPane().setBackground(COR_FUNDO);
    }
    
    private void inicializarComponentes() {
        // Header
        JPanel painelHeader = new JPanel(new BorderLayout());
        painelHeader.setBackground(COR_CARD);
        painelHeader.setPreferredSize(new Dimension(0, 80));
        painelHeader.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JLabel lblTitulo = new JLabel(edicao ? "✏️ EDITAR FUNCIONÁRIO" : "➕ NOVO FUNCIONÁRIO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_DESTAQUE);
        
        JLabel lblSubtitulo = new JLabel("Preencha todos os campos obrigatórios marcados com *");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitulo.setForeground(COR_TEXTO_SECUNDARIO);
        
        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.setBackground(COR_CARD);
        painelTextos.add(lblTitulo);
        painelTextos.add(Box.createVerticalStrut(5));
        painelTextos.add(lblSubtitulo);
        
        painelHeader.add(painelTextos, BorderLayout.WEST);
        add(painelHeader, BorderLayout.NORTH);
        
        // Formulário
        JPanel painelForm = criarFormulario();
        JScrollPane scroll = new JScrollPane(painelForm);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBackground(COR_FUNDO);
        add(scroll, BorderLayout.CENTER);
        
        // Botões
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }
    
    private JPanel criarFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Seção Foto
        painel.add(criarSecaoFoto());
        painel.add(Box.createVerticalStrut(20));
        
        // Seção Dados Básicos
        painel.add(criarSecao("📋 DADOS BÁSICOS", "Informações principais do funcionário"));
        painel.add(criarCampo("Nome Completo: *", txtNome = criarTextField(), true));
        
        JPanel painelLinha1 = new JPanel(new GridLayout(1, 2, 15, 0));
        painelLinha1.setBackground(COR_FUNDO);
        painelLinha1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        painelLinha1.add(criarCampo("CPF: *", txtCpf = criarTextField(), !edicao));
        painelLinha1.add(criarCampo("Email: *", txtEmail = criarTextField(), true));
        painel.add(painelLinha1);
        
        painel.add(criarCampo("Telefone: *", txtTelefone = criarTextField(), true));
        painel.add(Box.createVerticalStrut(20));
        
        // Seção Dados Pessoais
        painel.add(criarSecao("👤 DADOS PESSOAIS", "Informações complementares do funcionário"));
        
        JPanel painelLinha2 = new JPanel(new GridLayout(1, 2, 15, 0));
        painelLinha2.setBackground(COR_FUNDO);
        painelLinha2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        painelLinha2.add(criarCampo("RG:", txtRg = criarTextField(), true));
        painelLinha2.add(criarCampo("Data de Nascimento (dd/MM/yyyy):", txtDataNascimento = criarTextField(), true));
        painel.add(painelLinha2);
        
        painel.add(criarCampo("Endereço Completo:", txtEndereco = criarTextField(), true));
        painel.add(Box.createVerticalStrut(20));
        
        // Seção Dados Profissionais
        painel.add(criarSecao("💼 DADOS PROFISSIONAIS", "Informações sobre cargo e função"));
        
        JPanel painelLinha3 = new JPanel(new GridLayout(1, 2, 15, 0));
        painelLinha3.setBackground(COR_FUNDO);
        painelLinha3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        painelLinha3.add(criarCampo("Cargo:", txtCargo = criarTextField(), true));
        painelLinha3.add(criarCampo("Departamento:", txtDepartamento = criarTextField(), true));
        painel.add(painelLinha3);
        
        JPanel painelLinha4 = new JPanel(new GridLayout(1, 2, 15, 0));
        painelLinha4.setBackground(COR_FUNDO);
        painelLinha4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        painelLinha4.add(criarCampo("Salário (R$):", txtSalario = criarTextField(), true));
        
        JPanel painelStatus = new JPanel();
        painelStatus.setLayout(new BoxLayout(painelStatus, BoxLayout.Y_AXIS));
        painelStatus.setBackground(COR_FUNDO);
        JLabel lblStatus = criarLabel("Status:");
        lblStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbStatus = new JComboBox<>(new String[]{"Ativo", "Férias", "Afastado", "Demitido"});
        cmbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbStatus.setBackground(COR_CARD);
        cmbStatus.setForeground(COR_TEXTO_PRINCIPAL);
        cmbStatus.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cmbStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelStatus.add(lblStatus);
        painelStatus.add(Box.createVerticalStrut(8));
        painelStatus.add(cmbStatus);
        painelLinha4.add(painelStatus);
        
        painel.add(painelLinha4);
        
        // Descrição do cargo
        JPanel painelDescricao = new JPanel();
        painelDescricao.setLayout(new BoxLayout(painelDescricao, BoxLayout.Y_AXIS));
        painelDescricao.setBackground(COR_FUNDO);
        painelDescricao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        JLabel lblDescricao = criarLabel("Descrição do Cargo:");
        lblDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        txtDescricaoCargo = new JTextArea(4, 20);
        txtDescricaoCargo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDescricaoCargo.setBackground(COR_CARD);
        txtDescricaoCargo.setForeground(COR_TEXTO_PRINCIPAL);
        txtDescricaoCargo.setCaretColor(COR_DESTAQUE);
        txtDescricaoCargo.setLineWrap(true);
        txtDescricaoCargo.setWrapStyleWord(true);
        txtDescricaoCargo.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        
        JScrollPane scrollDesc = new JScrollPane(txtDescricaoCargo);
        scrollDesc.setBorder(new LineBorder(COR_BORDA, 1, true));
        scrollDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painelDescricao.add(lblDescricao);
        painelDescricao.add(Box.createVerticalStrut(8));
        painelDescricao.add(scrollDesc);
        painel.add(painelDescricao);
        
        return painel;
    }
    
    private JPanel criarSecaoFoto() {
        JPanel painel = new JPanel(new BorderLayout(20, 0));
        painel.setBackground(COR_CARD);
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        
        // Preview da foto
        JPanel painelPreview = new JPanel();
        painelPreview.setBackground(COR_CARD);
        painelPreview.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        lblFotoPreview = new JLabel();
        lblFotoPreview.setPreferredSize(new Dimension(130, 130));
        lblFotoPreview.setBackground(new Color(15, 23, 30));
        lblFotoPreview.setOpaque(true);
        lblFotoPreview.setBorder(new LineBorder(COR_BORDA, 2, true));
        lblFotoPreview.setHorizontalAlignment(SwingConstants.CENTER);
        lblFotoPreview.setText("👤");
        lblFotoPreview.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        lblFotoPreview.setForeground(COR_TEXTO_SECUNDARIO);
        
        painelPreview.add(lblFotoPreview);
        
        // Informações e botão
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setBackground(COR_CARD);
        
        JLabel lblTitulo = new JLabel("📸 FOTO DO FUNCIONÁRIO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COR_DESTAQUE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblInfo = new JLabel("Adicione uma foto para melhor identificação");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblInfo.setForeground(COR_TEXTO_SECUNDARIO);
        lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton btnEscolherFoto = new JButton("📁 Escolher Foto");
        btnEscolherFoto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEscolherFoto.setBackground(COR_INFO);
        btnEscolherFoto.setForeground(Color.WHITE);
        btnEscolherFoto.setFocusPainted(false);
        btnEscolherFoto.setBorderPainted(false);
        btnEscolherFoto.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEscolherFoto.setMaximumSize(new Dimension(200, 40));
        btnEscolherFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEscolherFoto.addActionListener(e -> escolherFoto());
        
        painelInfo.add(lblTitulo);
        painelInfo.add(Box.createVerticalStrut(5));
        painelInfo.add(lblInfo);
        painelInfo.add(Box.createVerticalStrut(15));
        painelInfo.add(btnEscolherFoto);
        
        painel.add(painelPreview, BorderLayout.WEST);
        painel.add(painelInfo, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarSecao(String titulo, String descricao) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_FUNDO);
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COR_DESTAQUE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblDescricao = new JLabel(descricao);
        lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescricao.setForeground(COR_TEXTO_SECUNDARIO);
        lblDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(3));
        painel.add(lblDescricao);
        
        return painel;
    }
    
    private JPanel criarCampo(String label, JTextField campo, boolean editavel) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_FUNDO);
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JLabel lbl = criarLabel(label);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        campo.setEnabled(editavel);
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(8));
        painel.add(campo);
        
        return painel;
    }
    
    private JLabel criarLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(COR_TEXTO_PRINCIPAL);
        return lbl;
    }
    
    private JTextField criarTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBackground(COR_CARD);
        txt.setForeground(COR_TEXTO_PRINCIPAL);
        txt.setCaretColor(COR_DESTAQUE);
        txt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return txt;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        painel.setBackground(COR_CARD);
        painel.setBorder(new MatteBorder(1, 0, 0, 0, COR_BORDA));
        
        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(71, 85, 105));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setPreferredSize(new Dimension(140, 45));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnSalvar = new JButton(edicao ? "✅ Atualizar" : "✅ Cadastrar");
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSalvar.setBackground(COR_DESTAQUE);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBorderPainted(false);
        btnSalvar.setPreferredSize(new Dimension(140, 45));
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(e -> salvar());
        
        painel.add(btnCancelar);
        painel.add(btnSalvar);
        
        return painel;
    }
    
    private void escolherFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar Foto");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));
        
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            caminhoFotoSelecionada = arquivo.getAbsolutePath();
            
            try {
                ImageIcon icon = new ImageIcon(caminhoFotoSelecionada);
                Image img = icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                lblFotoPreview.setIcon(new ImageIcon(img));
                lblFotoPreview.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar imagem!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void preencherDados() {
        txtNome.setText(funcionario.getNome());
        txtCpf.setText(funcionario.getCpf());
        txtEmail.setText(funcionario.getEmail());
        txtTelefone.setText(funcionario.getTelefone());
        txtRg.setText(funcionario.getRg() != null ? funcionario.getRg() : "");
        txtDataNascimento.setText(funcionario.getDataNascimento() != null ? funcionario.getDataNascimento() : "");
        txtEndereco.setText(funcionario.getEndereco() != null ? funcionario.getEndereco() : "");
        txtCargo.setText(funcionario.getCargo() != null ? funcionario.getCargo() : "");
        txtDepartamento.setText(funcionario.getDepartamento() != null ? funcionario.getDepartamento() : "");
        txtSalario.setText(funcionario.getSalarioAtual() > 0 ? String.valueOf(funcionario.getSalarioAtual()) : "");
        txtDescricaoCargo.setText(funcionario.getDescricaoCargo() != null ? funcionario.getDescricaoCargo() : "");
        cmbStatus.setSelectedItem(funcionario.getStatus());
        
        if (funcionario.getFotoCaminho() != null && !funcionario.getFotoCaminho().isEmpty()) {
            caminhoFotoSelecionada = funcionario.getFotoCaminho();
            try {
                ImageIcon icon = new ImageIcon(caminhoFotoSelecionada);
                Image img = icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                lblFotoPreview.setIcon(new ImageIcon(img));
                lblFotoPreview.setText("");
            } catch (Exception e) {
                // Manter ícone padrão
            }
        }
    }
    
    private void salvar() {
        // Validações
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();
        
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "⚠️ Preencha todos os campos obrigatórios (*)!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!edicao && gerenciador.cpfJaCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this, 
                "❌ Este CPF já está cadastrado!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!edicao && gerenciador.emailJaCadastrado(email)) {
            JOptionPane.showMessageDialog(this, 
                "❌ Este email já está cadastrado!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if (!edicao) {
                funcionario = new Funcionario(nome, cpf, email, telefone);
                gerenciador.adicionarFuncionario(funcionario);
            } else {
                funcionario.setNome(nome);
                funcionario.setEmail(email);
                funcionario.setTelefone(telefone);
            }
            
            // Atualizar dados pessoais
            funcionario.setRg(txtRg.getText().trim());
            funcionario.setDataNascimento(txtDataNascimento.getText().trim());
            funcionario.setEndereco(txtEndereco.getText().trim());
            
            // Atualizar dados profissionais
            funcionario.setCargo(txtCargo.getText().trim());
            funcionario.setDepartamento(txtDepartamento.getText().trim());
            funcionario.setDescricaoCargo(txtDescricaoCargo.getText().trim());
            funcionario.setStatus((String) cmbStatus.getSelectedItem());
            
            if (!txtSalario.getText().trim().isEmpty()) {
                double salario = Double.parseDouble(txtSalario.getText().trim().replace(",", "."));
                funcionario.setSalarioAtual(salario);
            }
            
            if (caminhoFotoSelecionada != null) {
                funcionario.setFotoCaminho(caminhoFotoSelecionada);
            }
            
            gerenciador.atualizarFuncionario(funcionario);
            
            JOptionPane.showMessageDialog(this, 
                "✅ " + (edicao ? "Dados atualizados" : "Funcionário cadastrado") + " com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Valor do salário inválido!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
