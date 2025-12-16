package Interacao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DialogAdvertencia extends JDialog {
    private Funcionario funcionario;
    private GerenciadorFuncionarios gerenciador;
    
    private JComboBox<String> cmbGravidade;
    private JTextField txtMotivo;
    private JTextArea txtDescricao;
    private JTextField txtAplicadaPor;
    
    private final Color COR_PRIMARIA = new Color(45, 52, 54);
    private final Color COR_PERIGO = new Color(255, 71, 87);
    private final Color COR_BRANCO = Color.WHITE;
    
    public DialogAdvertencia(Frame parent, Funcionario funcionario, GerenciadorFuncionarios gerenciador) {
        super(parent, "Nova Advertência", true);
        this.funcionario = funcionario;
        this.gerenciador = gerenciador;
        
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(COR_PERIGO);
        painelTitulo.setPreferredSize(new Dimension(0, 50));
        JLabel lblTitulo = new JLabel("⚠️ Registrar Nova Advertência");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        painelTitulo.add(lblTitulo);
        add(painelTitulo, BorderLayout.NORTH);
        
        // Formulário
        JPanel painelForm = new JPanel();
        painelForm.setLayout(new BoxLayout(painelForm, BoxLayout.Y_AXIS));
        painelForm.setBackground(COR_BRANCO);
        painelForm.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        painelForm.add(criarCampoGravidade());
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampoMotivo());
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampoDescricao());
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampoAplicadaPor());
        
        add(painelForm, BorderLayout.CENTER);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.setBackground(COR_BRANCO);
        
        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnSalvar = new JButton("✅ Confirmar");
        btnSalvar.setBackground(COR_PERIGO);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> salvar());
        
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel criarCampoGravidade() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Gravidade:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        cmbGravidade = new JComboBox<>(new String[]{"Leve", "Média", "Grave"});
        cmbGravidade.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(cmbGravidade);
        
        return painel;
    }
    
    private JPanel criarCampoMotivo() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Motivo:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtMotivo = new JTextField();
        txtMotivo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtMotivo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(txtMotivo);
        
        return painel;
    }
    
    private JPanel criarCampoDescricao() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Descrição Detalhada:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtDescricao = new JTextArea(6, 20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(txtDescricao);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(scroll);
        
        return painel;
    }
    
    private JPanel criarCampoAplicadaPor() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Aplicada por:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtAplicadaPor = new JTextField();
        txtAplicadaPor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtAplicadaPor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(txtAplicadaPor);
        
        return painel;
    }
    
    private void salvar() {
        String gravidade = (String) cmbGravidade.getSelectedItem();
        String motivo = txtMotivo.getText().trim();
        String descricao = txtDescricao.getText().trim();
        String aplicadaPor = txtAplicadaPor.getText().trim();
        
        if (motivo.isEmpty() || descricao.isEmpty() || aplicadaPor.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha todos os campos!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Confirma a aplicação desta advertência?\nEsta ação será registrada permanentemente.",
            "Confirmar Advertência",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            Funcionario.Advertencia advertencia = new Funcionario.Advertencia(gravidade, motivo, descricao, aplicadaPor);
            funcionario.adicionarAdvertencia(advertencia);
            gerenciador.atualizarFuncionario(funcionario);
            
            JOptionPane.showMessageDialog(this, 
                "Advertência registrada com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
        }
    }
}
