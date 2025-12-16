package Interacao;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PerfilFuncionario extends JFrame {
    private Funcionario funcionario;
    private GerenciadorFuncionarios gerenciador;
    
    // Cores
    private final Color COR_PRIMARIA = new Color(45, 52, 54);
    private final Color COR_SECUNDARIA = new Color(99, 110, 114);
    private final Color COR_DESTAQUE = new Color(0, 184, 148);
    private final Color COR_PERIGO = new Color(255, 71, 87);
    private final Color COR_AVISO = new Color(253, 203, 110);
    private final Color COR_SUCESSO = new Color(85, 239, 196);
    private final Color COR_FUNDO = new Color(245, 246, 250);
    private final Color COR_BRANCO = Color.WHITE;
    
    public PerfilFuncionario(Funcionario funcionario, GerenciadorFuncionarios gerenciador) {
        this.funcionario = funcionario;
        this.gerenciador = gerenciador;
        
        configurarJanela();
        inicializarComponentes();
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("Perfil do Funcionário - " + funcionario.getNome());
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COR_FUNDO);
    }
    
    private void inicializarComponentes() {
        // Header
        add(criarHeader(), BorderLayout.NORTH);
        
        // Conteúdo principal com scroll
        JScrollPane scrollPane = new JScrollPane(criarConteudoPrincipal());
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
        
        // Painel de ações
        add(criarPainelAcoes(), BorderLayout.SOUTH);
    }
    
    private JPanel criarHeader() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_PRIMARIA);
        painel.setPreferredSize(new Dimension(0, 200));
        painel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Foto e informações básicas
        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        painelEsquerda.setBackground(COR_PRIMARIA);
        
        // Foto do funcionário
        JLabel lblFoto = criarLabelFoto();
        painelEsquerda.add(lblFoto);
        
        // Informações principais
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setBackground(COR_PRIMARIA);
        
        JLabel lblNome = new JLabel(funcionario.getNome());
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblNome.setForeground(COR_BRANCO);
        
        JLabel lblCargo = new JLabel(funcionario.getCargo() != null ? funcionario.getCargo() : "Cargo não definido");
        lblCargo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblCargo.setForeground(new Color(189, 195, 199));
        
        JLabel lblDepartamento = new JLabel(funcionario.getDepartamento() != null ? "📍 " + funcionario.getDepartamento() : "");
        lblDepartamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDepartamento.setForeground(new Color(189, 195, 199));
        
        painelInfo.add(lblNome);
        painelInfo.add(Box.createVerticalStrut(5));
        painelInfo.add(lblCargo);
        painelInfo.add(Box.createVerticalStrut(5));
        painelInfo.add(lblDepartamento);
        
        painelEsquerda.add(painelInfo);
        
        // Status badge
        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelDireita.setBackground(COR_PRIMARIA);
        
        JLabel lblStatus = new JLabel("● " + funcionario.getStatus());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setForeground(funcionario.getStatus().equals("Ativo") ? COR_SUCESSO : COR_AVISO);
        lblStatus.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        painelDireita.add(lblStatus);
        
        painel.add(painelEsquerda, BorderLayout.WEST);
        painel.add(painelDireita, BorderLayout.EAST);
        
        return painel;
    }
    
    private JLabel criarLabelFoto() {
        JLabel lblFoto = new JLabel();
        lblFoto.setPreferredSize(new Dimension(150, 150));
        lblFoto.setOpaque(true);
        lblFoto.setBackground(COR_SECUNDARIA);
        lblFoto.setBorder(new LineBorder(COR_BRANCO, 3));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        
        if (funcionario.getFotoCaminho() != null && !funcionario.getFotoCaminho().isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(funcionario.getFotoCaminho());
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                lblFoto.setText("👤");
                lblFoto.setFont(new Font("Segoe UI", Font.PLAIN, 60));
                lblFoto.setForeground(COR_BRANCO);
            }
        } else {
            lblFoto.setText("👤");
            lblFoto.setFont(new Font("Segoe UI", Font.PLAIN, 60));
            lblFoto.setForeground(COR_BRANCO);
        }
        
        return lblFoto;
    }
    
    private JPanel criarConteudoPrincipal() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Dados pessoais e profissionais
        JPanel painelGrid = new JPanel(new GridLayout(1, 2, 20, 0));
        painelGrid.setBackground(COR_FUNDO);
        painelGrid.add(criarPainelDadosPessoais());
        painelGrid.add(criarPainelDadosProfissionais());
        painel.add(painelGrid);
        
        painel.add(Box.createVerticalStrut(20));
        
        // Histórico salarial
        painel.add(criarPainelHistoricoSalarial());
        painel.add(Box.createVerticalStrut(20));
        
        // Ocorrências e Advertências
        JPanel painelRegistros = new JPanel(new GridLayout(1, 2, 20, 0));
        painelRegistros.setBackground(COR_FUNDO);
        painelRegistros.add(criarPainelOcorrencias());
        painelRegistros.add(criarPainelAdvertencias());
        painel.add(painelRegistros);
        
        return painel;
    }
    
    private JPanel criarPainelDadosPessoais() {
        JPanel painel = criarPainelCard("📋 Dados Pessoais");
        
        adicionarLinha(painel, "CPF:", funcionario.getCpf());
        adicionarLinha(painel, "RG:", funcionario.getRg() != null ? funcionario.getRg() : "Não informado");
        adicionarLinha(painel, "Data de Nascimento:", funcionario.getDataNascimento() != null ? funcionario.getDataNascimento() : "Não informado");
        adicionarLinha(painel, "Email:", funcionario.getEmail());
        adicionarLinha(painel, "Telefone:", funcionario.getTelefone());
        adicionarLinha(painel, "Endereço:", funcionario.getEndereco() != null ? funcionario.getEndereco() : "Não informado");
        
        return painel;
    }
    
    private JPanel criarPainelDadosProfissionais() {
        JPanel painel = criarPainelCard("💼 Dados Profissionais");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        
        adicionarLinha(painel, "Cargo:", funcionario.getCargo() != null ? funcionario.getCargo() : "Não definido");
        adicionarLinha(painel, "Departamento:", funcionario.getDepartamento() != null ? funcionario.getDepartamento() : "Não definido");
        adicionarLinha(painel, "Salário Atual:", funcionario.getSalarioAtual() > 0 ? currencyFormat.format(funcionario.getSalarioAtual()) : "Não definido");
        adicionarLinha(painel, "Data de Admissão:", funcionario.getDataAdmissao() != null ? sdf.format(funcionario.getDataAdmissao()) : "Não informado");
        adicionarLinha(painel, "Status:", funcionario.getStatus());
        
        if (funcionario.getDescricaoCargo() != null && !funcionario.getDescricaoCargo().isEmpty()) {
            painel.add(Box.createVerticalStrut(10));
            JLabel lblDescTitulo = new JLabel("Descrição do Cargo:");
            lblDescTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lblDescTitulo.setForeground(COR_PRIMARIA);
            painel.add(lblDescTitulo);
            
            JTextArea txtDesc = new JTextArea(funcionario.getDescricaoCargo());
            txtDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            txtDesc.setLineWrap(true);
            txtDesc.setWrapStyleWord(true);
            txtDesc.setEditable(false);
            txtDesc.setBackground(COR_FUNDO);
            txtDesc.setBorder(new EmptyBorder(5, 0, 0, 0));
            painel.add(txtDesc);
        }
        
        return painel;
    }
    
    private JPanel criarPainelHistoricoSalarial() {
        JPanel painel = criarPainelCard("💰 Histórico Salarial");
        
        if (funcionario.getHistoricoSalarial().isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhum histórico registrado");
            lblVazio.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblVazio.setForeground(COR_SECUNDARIA);
            painel.add(lblVazio);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            
            for (Funcionario.HistoricoSalarial hist : funcionario.getHistoricoSalarial()) {
                JPanel itemPanel = new JPanel(new BorderLayout(10, 5));
                itemPanel.setBackground(COR_FUNDO);
                itemPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
                
                double diferenca = hist.getSalarioNovo() - hist.getSalarioAnterior();
                String simbolo = diferenca > 0 ? "📈" : "📉";
                
                JLabel lblData = new JLabel(simbolo + " " + sdf.format(hist.getData()));
                lblData.setFont(new Font("Segoe UI", Font.BOLD, 12));
                
                JLabel lblValores = new JLabel(currencyFormat.format(hist.getSalarioAnterior()) + " → " + currencyFormat.format(hist.getSalarioNovo()));
                lblValores.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                
                JLabel lblMotivo = new JLabel(hist.getMotivo());
                lblMotivo.setFont(new Font("Segoe UI", Font.ITALIC, 11));
                lblMotivo.setForeground(COR_SECUNDARIA);
                
                JPanel painelTextos = new JPanel();
                painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
                painelTextos.setBackground(COR_FUNDO);
                painelTextos.add(lblData);
                painelTextos.add(lblValores);
                painelTextos.add(lblMotivo);
                
                itemPanel.add(painelTextos, BorderLayout.CENTER);
                painel.add(itemPanel);
            }
        }
        
        return painel;
    }
    
    private JPanel criarPainelOcorrencias() {
        JPanel painel = criarPainelCard("📌 Ocorrências (" + funcionario.getOcorrencias().size() + ")");
        
        if (funcionario.getOcorrencias().isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhuma ocorrência registrada");
            lblVazio.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblVazio.setForeground(COR_SECUNDARIA);
            painel.add(lblVazio);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            for (Funcionario.Ocorrencia ocor : funcionario.getOcorrencias()) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
                itemPanel.setBackground(COR_FUNDO);
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(220, 221, 225), 1),
                    new EmptyBorder(10, 10, 10, 10)
                ));
                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                
                Color corTipo = ocor.getTipo().equals("Positiva") ? COR_SUCESSO : 
                               ocor.getTipo().equals("Negativa") ? COR_PERIGO : COR_AVISO;
                
                JLabel lblTipo = new JLabel("● " + ocor.getTipo());
                lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblTipo.setForeground(corTipo);
                
                JLabel lblDescricao = new JLabel(ocor.getDescricao());
                lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                
                JLabel lblInfo = new JLabel(sdf.format(ocor.getData()) + " - " + ocor.getRegistradoPor());
                lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 10));
                lblInfo.setForeground(COR_SECUNDARIA);
                
                itemPanel.add(lblTipo);
                itemPanel.add(Box.createVerticalStrut(3));
                itemPanel.add(lblDescricao);
                itemPanel.add(Box.createVerticalStrut(3));
                itemPanel.add(lblInfo);
                
                painel.add(itemPanel);
                painel.add(Box.createVerticalStrut(10));
            }
        }
        
        return painel;
    }
    
    private JPanel criarPainelAdvertencias() {
        JPanel painel = criarPainelCard("⚠️ Advertências (" + funcionario.getAdvertencias().size() + ")");
        
        if (funcionario.getAdvertencias().isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhuma advertência registrada");
            lblVazio.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblVazio.setForeground(COR_SECUNDARIA);
            painel.add(lblVazio);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            for (Funcionario.Advertencia adv : funcionario.getAdvertencias()) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
                itemPanel.setBackground(new Color(255, 245, 245));
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(COR_PERIGO, 2),
                    new EmptyBorder(10, 10, 10, 10)
                ));
                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
                
                JLabel lblGravidade = new JLabel("🚨 " + adv.getGravidade().toUpperCase());
                lblGravidade.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblGravidade.setForeground(COR_PERIGO);
                
                JLabel lblMotivo = new JLabel("Motivo: " + adv.getMotivo());
                lblMotivo.setFont(new Font("Segoe UI", Font.BOLD, 12));
                
                JLabel lblDescricao = new JLabel(adv.getDescricao());
                lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                
                JLabel lblInfo = new JLabel(sdf.format(adv.getData()) + " - Aplicada por: " + adv.getAplicadaPor());
                lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 10));
                lblInfo.setForeground(COR_SECUNDARIA);
                
                itemPanel.add(lblGravidade);
                itemPanel.add(Box.createVerticalStrut(3));
                itemPanel.add(lblMotivo);
                itemPanel.add(Box.createVerticalStrut(3));
                itemPanel.add(lblDescricao);
                itemPanel.add(Box.createVerticalStrut(3));
                itemPanel.add(lblInfo);
                
                painel.add(itemPanel);
                painel.add(Box.createVerticalStrut(10));
            }
        }
        
        return painel;
    }
    
    private JPanel criarPainelCard(String titulo) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_BRANCO);
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 221, 225), 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COR_PRIMARIA);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(15));
        
        return painel;
    }
    
    private void adicionarLinha(JPanel painel, String label, String valor) {
        JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
        linha.setBackground(COR_BRANCO);
        
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblLabel.setForeground(COR_PRIMARIA);
        lblLabel.setPreferredSize(new Dimension(180, 20));
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblValor.setForeground(COR_SECUNDARIA);
        
        linha.add(lblLabel);
        linha.add(lblValor);
        painel.add(linha);
    }
    
    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painel.setBackground(COR_BRANCO);
        painel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        JButton btnEditarDados = criarBotao("✏️ Editar Dados", COR_DESTAQUE);
        btnEditarDados.addActionListener(e -> abrirEdicaoDados());
        
        JButton btnNovaOcorrencia = criarBotao("➕ Nova Ocorrência", COR_SECUNDARIA);
        btnNovaOcorrencia.addActionListener(e -> abrirNovaOcorrencia());
        
        JButton btnNovaAdvertencia = criarBotao("⚠️ Nova Advertência", COR_PERIGO);
        btnNovaAdvertencia.addActionListener(e -> abrirNovaAdvertencia());
        
        JButton btnFechar = criarBotao("❌ Fechar", COR_SECUNDARIA);
        btnFechar.addActionListener(e -> dispose());
        
        painel.add(btnEditarDados);
        painel.add(btnNovaOcorrencia);
        painel.add(btnNovaAdvertencia);
        painel.add(btnFechar);
        
        return painel;
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(cor);
        btn.setForeground(COR_BRANCO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(180, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private void abrirEdicaoDados() {
        new EdicaoFuncionario(funcionario, gerenciador, this);
    }
    
    private void abrirNovaOcorrencia() {
        new DialogOcorrencia(this, funcionario, gerenciador);
    }
    
    private void abrirNovaAdvertencia() {
        new DialogAdvertencia(this, funcionario, gerenciador);
    }
    
    public void atualizarPerfil() {
        getContentPane().removeAll();
        inicializarComponentes();
        revalidate();
        repaint();
    }
}
