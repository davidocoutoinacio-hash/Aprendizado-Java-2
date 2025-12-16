package Interacao;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EdicaoFuncionario extends JDialog {
    private Funcionario funcionario;
    private GerenciadorFuncionarios gerenciador;
    private PerfilFuncionario perfilFuncionario;
    
    // Componentes
    private JTextField txtRg, txtDataNascimento, txtEndereco;
    private JTextField txtCargo, txtDepartamento, txtSalario, txtDescricaoCargo;
    private JComboBox<String> cmbStatus;
    private JButton btnSalvar, btnCancelar, btnAjusteSalarial;
    
    // Cores
    private final Color COR_PRIMARIA = new Color(45, 52, 54);
    private final Color COR_DESTAQUE = new Color(0, 184, 148);
    private final Color COR_SECUNDARIA = new Color(99, 110, 114);
    private final Color COR_FUNDO = new Color(245, 246, 250);
    private final Color COR_BRANCO = Color.WHITE;
    
    public EdicaoFuncionario(Funcionario funcionario, GerenciadorFuncionarios gerenciador, PerfilFuncionario perfilFuncionario) {
        super(perfilFuncionario, "Editar Dados do Funcionário", true);
        this.funcionario = funcionario;
        this.gerenciador = gerenciador;
        this.perfilFuncionario = perfilFuncionario;
        
        configurarJanela();
        inicializarComponentes();
        preencherCampos();
        setVisible(true);
    }
    
    private void configurarJanela() {
        setSize(700, 650);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        getContentPane().setBackground(COR_FUNDO);
    }
    
    private void inicializarComponentes() {
        // Título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(COR_PRIMARIA);
        painelTitulo.setPreferredSize(new Dimension(0, 60));
        JLabel lblTitulo = new JLabel("✏️ Editar Dados do Funcionário");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        painelTitulo.add(lblTitulo);
        add(painelTitulo, BorderLayout.NORTH);
        
        // Formulário
        JPanel painelForm = new JPanel();
        painelForm.setLayout(new BoxLayout(painelForm, BoxLayout.Y_AXIS));
        painelForm.setBackground(COR_BRANCO);
        painelForm.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Dados Pessoais
        painelForm.add(criarSecao("Dados Pessoais"));
        painelForm.add(criarCampo("RG:", txtRg = new JTextField()));
        painelForm.add(criarCampo("Data de Nascimento (dd/MM/yyyy):", txtDataNascimento = new JTextField()));
        painelForm.add(criarCampo("Endereço:", txtEndereco = new JTextField()));
        
        painelForm.add(Box.createVerticalStrut(20));
        
        // Dados Profissionais
        painelForm.add(criarSecao("Dados Profissionais"));
        painelForm.add(criarCampo("Cargo:", txtCargo = new JTextField()));
        painelForm.add(criarCampo("Departamento:", txtDepartamento = new JTextField()));
        painelForm.add(criarCampo("Salário Atual (R$):", txtSalario = new JTextField()));
        painelForm.add(criarCampo("Descrição do Cargo:", txtDescricaoCargo = new JTextField()));
        
        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelStatus.setBackground(COR_BRANCO);
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblStatus.setPreferredSize(new Dimension(200, 25));
        cmbStatus = new JComboBox<>(new String[]{"Ativo", "Férias", "Afastado", "Demitido"});
        cmbStatus.setPreferredSize(new Dimension(200, 30));
        painelStatus.add(lblStatus);
        painelStatus.add(cmbStatus);
        painelForm.add(painelStatus);
        
        painelForm.add(Box.createVerticalStrut(10));
        
        // Botão ajuste salarial
        btnAjusteSalarial = criarBotao("💰 Registrar Ajuste Salarial", COR_DESTAQUE);
        btnAjusteSalarial.addActionListener(e -> abrirAjusteSalarial());
        btnAjusteSalarial.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelForm.add(btnAjusteSalarial);
        
        JScrollPane scroll = new JScrollPane(painelForm);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
        
        // Botões de ação
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.setBackground(COR_BRANCO);
        
        btnCancelar = criarBotao("❌ Cancelar", COR_SECUNDARIA);
        btnCancelar.addActionListener(e -> dispose());
        
        btnSalvar = criarBotao("✅ Salvar", COR_DESTAQUE);
        btnSalvar.addActionListener(e -> salvarDados());
        
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);
    }
    
    private JLabel criarSecao(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(COR_PRIMARIA);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }
    
    private JPanel criarCampo(String label, JTextField campo) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.setBackground(COR_BRANCO);
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setPreferredSize(new Dimension(250, 25));
        
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setPreferredSize(new Dimension(350, 30));
        
        painel.add(lbl);
        painel.add(campo);
        return painel;
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(200, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private void preencherCampos() {
        txtRg.setText(funcionario.getRg() != null ? funcionario.getRg() : "");
        txtDataNascimento.setText(funcionario.getDataNascimento() != null ? funcionario.getDataNascimento() : "");
        txtEndereco.setText(funcionario.getEndereco() != null ? funcionario.getEndereco() : "");
        txtCargo.setText(funcionario.getCargo() != null ? funcionario.getCargo() : "");
        txtDepartamento.setText(funcionario.getDepartamento() != null ? funcionario.getDepartamento() : "");
        txtSalario.setText(funcionario.getSalarioAtual() > 0 ? String.valueOf(funcionario.getSalarioAtual()) : "");
        txtDescricaoCargo.setText(funcionario.getDescricaoCargo() != null ? funcionario.getDescricaoCargo() : "");
        cmbStatus.setSelectedItem(funcionario.getStatus());
    }
    
    private void salvarDados() {
        try {
            funcionario.setRg(txtRg.getText().trim());
            funcionario.setDataNascimento(txtDataNascimento.getText().trim());
            funcionario.setEndereco(txtEndereco.getText().trim());
            funcionario.setCargo(txtCargo.getText().trim());
            funcionario.setDepartamento(txtDepartamento.getText().trim());
            funcionario.setDescricaoCargo(txtDescricaoCargo.getText().trim());
            funcionario.setStatus((String) cmbStatus.getSelectedItem());
            
            if (!txtSalario.getText().trim().isEmpty()) {
                double salario = Double.parseDouble(txtSalario.getText().trim().replace(",", "."));
                funcionario.setSalarioAtual(salario);
            }
            
            gerenciador.atualizarFuncionario(funcionario);
            
            JOptionPane.showMessageDialog(this, 
                "Dados atualizados com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            perfilFuncionario.atualizarPerfil();
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro: Salário deve ser um número válido!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void abrirAjusteSalarial() {
        new DialogAjusteSalarial(this, funcionario, gerenciador);
    }
}
