package Interacao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DialogAjusteSalarial extends JDialog {
    private Funcionario funcionario;
    private GerenciadorFuncionarios gerenciador;
    
    private JTextField txtSalarioAtual, txtNovoSalario, txtMotivo;
    private JLabel lblDiferenca, lblPercentual;
    
    private final Color COR_PRIMARIA = new Color(45, 52, 54);
    private final Color COR_DESTAQUE = new Color(0, 184, 148);
    private final Color COR_PERIGO = new Color(255, 71, 87);
    private final Color COR_BRANCO = Color.WHITE;
    
    public DialogAjusteSalarial(Dialog parent, Funcionario funcionario, GerenciadorFuncionarios gerenciador) {
        super(parent, "Ajuste Salarial", true);
        this.funcionario = funcionario;
        this.gerenciador = gerenciador;
        
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(COR_PRIMARIA);
        painelTitulo.setPreferredSize(new Dimension(0, 50));
        JLabel lblTitulo = new JLabel("💰 Registrar Ajuste Salarial");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        painelTitulo.add(lblTitulo);
        add(painelTitulo, BorderLayout.NORTH);
        
        // Formulário
        JPanel painelForm = new JPanel();
        painelForm.setLayout(new BoxLayout(painelForm, BoxLayout.Y_AXIS));
        painelForm.setBackground(COR_BRANCO);
        painelForm.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        painelForm.add(criarCampoSalarioAtual());
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampoNovoSalario());
        painelForm.add(Box.createVerticalStrut(10));
        painelForm.add(criarPainelDiferenca());
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampoMotivo());
        
        add(painelForm, BorderLayout.CENTER);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.setBackground(COR_BRANCO);
        
        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnSalvar = new JButton("✅ Confirmar");
        btnSalvar.setBackground(COR_DESTAQUE);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> salvar());
        
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel criarCampoSalarioAtual() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Salário Atual:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtSalarioAtual = new JTextField(String.format("%.2f", funcionario.getSalarioAtual()));
        txtSalarioAtual.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtSalarioAtual.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSalarioAtual.setEditable(false);
        txtSalarioAtual.setBackground(new Color(240, 240, 240));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(txtSalarioAtual);
        
        return painel;
    }
    
    private JPanel criarCampoNovoSalario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Novo Salário:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtNovoSalario = new JTextField();
        txtNovoSalario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtNovoSalario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNovoSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                calcularDiferenca();
            }
        });
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(txtNovoSalario);
        
        return painel;
    }
    
    private JPanel criarPainelDiferenca() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(245, 246, 250));
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        lblDiferenca = new JLabel("Diferença: R$ 0,00");
        lblDiferenca.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        lblPercentual = new JLabel("Percentual: 0%");
        lblPercentual.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        painel.add(lblDiferenca);
        painel.add(lblPercentual);
        
        return painel;
    }
    
    private JPanel criarCampoMotivo() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        
        JLabel lbl = new JLabel("Motivo do Ajuste:");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtMotivo = new JTextField();
        txtMotivo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtMotivo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        painel.add(lbl);
        painel.add(Box.createVerticalStrut(5));
        painel.add(txtMotivo);
        
        return painel;
    }
    
    private void calcularDiferenca() {
        try {
            double salarioAtual = funcionario.getSalarioAtual();
            double novoSalario = Double.parseDouble(txtNovoSalario.getText().replace(",", "."));
            double diferenca = novoSalario - salarioAtual;
            double percentual = (diferenca / salarioAtual) * 100;
            
            lblDiferenca.setText(String.format("Diferença: R$ %.2f", diferenca));
            lblPercentual.setText(String.format("Percentual: %.2f%%", percentual));
            
            if (diferenca > 0) {
                lblDiferenca.setForeground(COR_DESTAQUE);
                lblPercentual.setForeground(COR_DESTAQUE);
            } else {
                lblDiferenca.setForeground(COR_PERIGO);
                lblPercentual.setForeground(COR_PERIGO);
            }
        } catch (NumberFormatException e) {
            lblDiferenca.setText("Diferença: R$ 0,00");
            lblPercentual.setText("Percentual: 0%");
            lblDiferenca.setForeground(Color.BLACK);
            lblPercentual.setForeground(Color.BLACK);
        }
    }
    
    private void salvar() {
        try {
            double novoSalario = Double.parseDouble(txtNovoSalario.getText().replace(",", "."));
            String motivo = txtMotivo.getText().trim();
            
            if (motivo.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, informe o motivo do ajuste salarial!", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (novoSalario == funcionario.getSalarioAtual()) {
                JOptionPane.showMessageDialog(this, 
                    "O novo salário deve ser diferente do atual!", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Registrar no histórico
            Funcionario.HistoricoSalarial historico = new Funcionario.HistoricoSalarial(
                funcionario.getSalarioAtual(), 
                novoSalario, 
                motivo
            );
            funcionario.adicionarHistoricoSalarial(historico);
            
            // Atualizar salário atual
            funcionario.setSalarioAtual(novoSalario);
            gerenciador.atualizarFuncionario(funcionario);
            
            JOptionPane.showMessageDialog(this, 
                "Ajuste salarial registrado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Valor do salário inválido!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
