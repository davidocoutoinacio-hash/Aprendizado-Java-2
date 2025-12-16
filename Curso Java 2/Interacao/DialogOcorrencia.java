package Interacao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DialogOcorrencia extends JDialog {
    private Funcionario funcionario;
    private GerenciadorFuncionarios gerenciador;
    
    private JComboBox<String> cmbTipo;
    private JTextArea txtDescricao;
    private JTextField txtRegistradoPor;
    
    private final Color COR_PRIMARIA = new Color(45, 52, 54);
    private final Color COR_DESTAQUE = new Color(0, 184, 148);
    private final Color COR_BRANCO = Color.WHITE;
    
    public DialogOcorrencia(Frame parent, Funcionario funcionario, GerenciadorFuncionarios gerenciador) {
        super(parent, "Nova Ocorrência", true);
        this.funcionario = funcionario;
        this.gerenciador = gerenciador;
        
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(COR_PRIMARIA);
        painelTitulo.setPreferredSize(new Dimension(0, 50));
        JLabel lblTitulo = new JLabel("📌 Registrar Nova Ocorrência");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        painelTitulo.add(lblTitulo);
        add(painelTitulo, BorderLayout.NORTH);
        
        // Formulário
        JPanel painelForm = new JPanel();
        painelForm.setLayout(new BoxLayout(painelForm, BoxLayout.Y_AXIS));
        painelForm.setBackground(COR_BRANCO);
        painelForm.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        painelForm.add(criarCampoCombo());
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampoDescricao());
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampoRegistradoPor());
        
        add(painelForm, BorderLayout.CENTER);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.setBackground(COR_BRANCO);
        
        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnSalvar = new JButton("✅ Salvar");
        btnSalvar.setBackground(COR_DESTAQUE);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> salvar());
        
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel criarCampoCombo() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Tipo de Ocorrência:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        cmbTipo = new JComboBox<>(new String[]{"Positiva", "Neutra", "Negativa"});
        cmbTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(cmbTipo);
        
        return painel;
    }
    
    private JPanel criarCampoDescricao() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Descrição da Ocorrência:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtDescricao = new JTextArea(5, 20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(txtDescricao);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(scroll);
        
        return painel;
    }
    
    private JPanel criarCampoRegistradoPor() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Registrado por:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtRegistradoPor = new JTextField();
        txtRegistradoPor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtRegistradoPor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(txtRegistradoPor);
        
        return painel;
    }
    
    private void salvar() {
        String tipo = (String) cmbTipo.getSelectedItem();
        String descricao = txtDescricao.getText().trim();
        String registradoPor = txtRegistradoPor.getText().trim();
        
        if (descricao.isEmpty() || registradoPor.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha todos os campos!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Funcionario.Ocorrencia ocorrencia = new Funcionario.Ocorrencia(tipo, descricao, registradoPor);
        funcionario.adicionarOcorrencia(ocorrencia);
        gerenciador.atualizarFuncionario(funcionario);
        
        JOptionPane.showMessageDialog(this, 
            "Ocorrência registrada com sucesso!", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }
}
