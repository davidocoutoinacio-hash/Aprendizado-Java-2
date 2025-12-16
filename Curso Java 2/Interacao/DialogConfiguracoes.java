package Interacao;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class DialogConfiguracoes extends JDialog {
    private Configuracoes config;
    private HubFuncionarios hub;
    
    private JComboBox<String> cmbTema;
    private JComboBox<String> cmbFonte;
    private JSlider sliderTamanhoFonte;
    private JLabel lblPreviewTamanho;
    private JPanel painelPreview;
    
    public DialogConfiguracoes(Frame parent, Configuracoes config, HubFuncionarios hub) {
        super(parent, "Configurações do Sistema", true);
        this.config = config;
        this.hub = hub;
        
        configurarJanela();
        inicializarComponentes();
        setVisible(true);
    }
    
    private void configurarJanela() {
        setSize(700, 600);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        getContentPane().setBackground(config.getCorFundoEscuro());
    }
    
    private void inicializarComponentes() {
        // Header
        JPanel painelHeader = new JPanel(new BorderLayout());
        painelHeader.setBackground(config.getCorCard());
        painelHeader.setPreferredSize(new Dimension(0, 80));
        painelHeader.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JLabel lblTitulo = new JLabel("⚙️ CONFIGURAÇÕES");
        lblTitulo.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 24));
        lblTitulo.setForeground(config.getCorDestaque());
        
        JLabel lblSubtitulo = new JLabel("Personalize a aparência do sistema");
        lblSubtitulo.setFont(new Font(config.getFontePrincipal(), Font.PLAIN, 13));
        lblSubtitulo.setForeground(config.getCorTextoSecundario());
        
        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.setBackground(config.getCorCard());
        painelTextos.add(lblTitulo);
        painelTextos.add(Box.createVerticalStrut(5));
        painelTextos.add(lblSubtitulo);
        
        painelHeader.add(painelTextos, BorderLayout.WEST);
        add(painelHeader, BorderLayout.NORTH);
        
        // Conteúdo
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBackground(config.getCorFundoEscuro());
        painelConteudo.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        // Seção Tema
        painelConteudo.add(criarSecao("🎨 TEMA DE CORES", "Escolha a paleta de cores do sistema"));
        painelConteudo.add(Box.createVerticalStrut(15));
        painelConteudo.add(criarSecaoTema());
        painelConteudo.add(Box.createVerticalStrut(30));
        
        // Seção Fonte
        painelConteudo.add(criarSecao("🔤 TIPOGRAFIA", "Ajuste a fonte e o tamanho do texto"));
        painelConteudo.add(Box.createVerticalStrut(15));
        painelConteudo.add(criarSecaoFonte());
        painelConteudo.add(Box.createVerticalStrut(20));
        painelConteudo.add(criarSecaoTamanho());
        painelConteudo.add(Box.createVerticalStrut(30));
        
        // Preview
        painelConteudo.add(criarSecao("👁️ PREVIEW", "Visualização das alterações"));
        painelConteudo.add(Box.createVerticalStrut(15));
        painelConteudo.add(criarPreview());
        
        JScrollPane scroll = new JScrollPane(painelConteudo);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
        
        // Botões
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }
    
    private JPanel criarSecao(String titulo, String descricao) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(config.getCorFundoEscuro());
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 16));
        lblTitulo.setForeground(config.getCorDestaque());
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblDescricao = new JLabel(descricao);
        lblDescricao.setFont(new Font(config.getFontePrincipal(), Font.PLAIN, 12));
        lblDescricao.setForeground(config.getCorTextoSecundario());
        lblDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(3));
        painel.add(lblDescricao);
        
        return painel;
    }
    
    private JPanel criarSecaoTema() {
        JPanel painel = new JPanel(new GridLayout(1, 5, 10, 0));
        painel.setBackground(config.getCorFundoEscuro());
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        String[] temas = {"Escuro", "Claro", "Azul", "Roxo", "Verde"};
        Color[][] coresTemas = {
            {new Color(23, 32, 42), new Color(52, 211, 153)},    // Escuro
            {new Color(255, 255, 255), new Color(16, 185, 129)}, // Claro
            {new Color(15, 23, 42), new Color(56, 189, 248)},    // Azul
            {new Color(24, 24, 27), new Color(168, 85, 247)},    // Roxo
            {new Color(20, 29, 32), new Color(52, 211, 153)}     // Verde
        };
        
        ButtonGroup grupo = new ButtonGroup();
        
        for (int i = 0; i < temas.length; i++) {
            painel.add(criarCardTema(temas[i], coresTemas[i], grupo, i));
        }
        
        return painel;
    }
    
    private JPanel criarCardTema(String nome, Color[] cores, ButtonGroup grupo, int index) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(config.getCorCard());
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 2, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Preview das cores
        JPanel painelCores = new JPanel(new GridLayout(1, 2, 3, 0));
        painelCores.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        painelCores.setOpaque(false);
        
        JPanel cor1 = new JPanel();
        cor1.setBackground(cores[0]);
        cor1.setPreferredSize(new Dimension(0, 40));
        
        JPanel cor2 = new JPanel();
        cor2.setBackground(cores[1]);
        cor2.setPreferredSize(new Dimension(0, 40));
        
        painelCores.add(cor1);
        painelCores.add(cor2);
        
        JRadioButton radio = new JRadioButton(nome);
        radio.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 11));
        radio.setForeground(config.getCorTextoPrincipal());
        radio.setBackground(config.getCorCard());
        radio.setAlignmentX(Component.CENTER_ALIGNMENT);
        radio.setHorizontalAlignment(SwingConstants.CENTER);
        
        if (config.getTemaAtual().ordinal() == index) {
            radio.setSelected(true);
        }
        
        grupo.add(radio);
        
        card.add(painelCores);
        card.add(Box.createVerticalStrut(8));
        card.add(radio);
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                radio.setSelected(true);
                atualizarPreview();
            }
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(config.getCorDestaque(), 2, true),
                    new EmptyBorder(10, 10, 10, 10)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (!radio.isSelected()) {
                    card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(config.getCorBorda(), 2, true),
                        new EmptyBorder(10, 10, 10, 10)
                    ));
                }
            }
        });
        
        return card;
    }
    
    private JPanel criarSecaoFonte() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(config.getCorCard());
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JLabel lblFonte = new JLabel("Família da Fonte:");
        lblFonte.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 13));
        lblFonte.setForeground(config.getCorTextoPrincipal());
        lblFonte.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        String[] fontes = {
            "Segoe UI", "Arial", "Verdana", "Tahoma", 
            "Georgia", "Times New Roman", "Courier New", "Consolas"
        };
        cmbFonte = new JComboBox<>(fontes);
        cmbFonte.setSelectedItem(config.getFontePrincipal());
        cmbFonte.setFont(new Font(config.getFontePrincipal(), Font.PLAIN, 13));
        cmbFonte.setBackground(config.getCorFundoEscuro());
        cmbFonte.setForeground(config.getCorTextoPrincipal());
        cmbFonte.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        cmbFonte.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbFonte.addActionListener(e -> atualizarPreview());
        
        painel.add(lblFonte);
        painel.add(Box.createVerticalStrut(8));
        painel.add(cmbFonte);
        
        return painel;
    }
    
    private JPanel criarSecaoTamanho() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(config.getCorCard());
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        JPanel painelHeader = new JPanel(new BorderLayout());
        painelHeader.setBackground(config.getCorCard());
        painelHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        
        JLabel lblTamanho = new JLabel("Tamanho da Fonte:");
        lblTamanho.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 13));
        lblTamanho.setForeground(config.getCorTextoPrincipal());
        
        lblPreviewTamanho = new JLabel(config.getTamanhoFonteBase() + "px");
        lblPreviewTamanho.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 13));
        lblPreviewTamanho.setForeground(config.getCorDestaque());
        
        painelHeader.add(lblTamanho, BorderLayout.WEST);
        painelHeader.add(lblPreviewTamanho, BorderLayout.EAST);
        
        sliderTamanhoFonte = new JSlider(10, 20, config.getTamanhoFonteBase());
        sliderTamanhoFonte.setBackground(config.getCorCard());
        sliderTamanhoFonte.setForeground(config.getCorDestaque());
        sliderTamanhoFonte.setMajorTickSpacing(2);
        sliderTamanhoFonte.setMinorTickSpacing(1);
        sliderTamanhoFonte.setPaintTicks(true);
        sliderTamanhoFonte.setPaintLabels(true);
        sliderTamanhoFonte.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        sliderTamanhoFonte.addChangeListener(e -> {
            lblPreviewTamanho.setText(sliderTamanhoFonte.getValue() + "px");
            atualizarPreview();
        });
        
        painel.add(painelHeader);
        painel.add(Box.createVerticalStrut(10));
        painel.add(sliderTamanhoFonte);
        
        return painel;
    }
    
    private JPanel criarPreview() {
        painelPreview = new JPanel();
        painelPreview.setLayout(new BoxLayout(painelPreview, BoxLayout.Y_AXIS));
        painelPreview.setBackground(config.getCorCard());
        painelPreview.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        painelPreview.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        
        atualizarPreview();
        
        return painelPreview;
    }
    
    private void atualizarPreview() {
        if (painelPreview == null) return;
        
        painelPreview.removeAll();
        
        String fonte = (String) cmbFonte.getSelectedItem();
        int tamanho = sliderTamanhoFonte.getValue();
        
        JLabel lblTitulo = new JLabel("Sistema de Gestão de Funcionários");
        lblTitulo.setFont(new Font(fonte, Font.BOLD, tamanho + 7));
        lblTitulo.setForeground(config.getCorTextoPrincipal());
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblNormal = new JLabel("Este é um exemplo de texto normal no sistema.");
        lblNormal.setFont(new Font(fonte, Font.PLAIN, tamanho));
        lblNormal.setForeground(config.getCorTextoSecundario());
        lblNormal.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblDestaque = new JLabel("✅ Texto em destaque com cor principal");
        lblDestaque.setFont(new Font(fonte, Font.BOLD, tamanho));
        lblDestaque.setForeground(config.getCorDestaque());
        lblDestaque.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painelPreview.add(lblTitulo);
        painelPreview.add(Box.createVerticalStrut(10));
        painelPreview.add(lblNormal);
        painelPreview.add(Box.createVerticalStrut(10));
        painelPreview.add(lblDestaque);
        
        painelPreview.revalidate();
        painelPreview.repaint();
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        painel.setBackground(config.getCorCard());
        painel.setBorder(new MatteBorder(1, 0, 0, 0, config.getCorBorda()));
        
        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 14));
        btnCancelar.setBackground(new Color(71, 85, 105));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setPreferredSize(new Dimension(140, 45));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnAplicar = new JButton("✅ Aplicar");
        btnAplicar.setFont(new Font(config.getFontePrincipal(), Font.BOLD, 14));
        btnAplicar.setBackground(config.getCorDestaque());
        btnAplicar.setForeground(Color.WHITE);
        btnAplicar.setFocusPainted(false);
        btnAplicar.setBorderPainted(false);
        btnAplicar.setPreferredSize(new Dimension(140, 45));
        btnAplicar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAplicar.addActionListener(e -> aplicarConfiguracoes());
        
        painel.add(btnCancelar);
        painel.add(btnAplicar);
        
        return painel;
    }
    
    private void aplicarConfiguracoes() {
        // Obter tema selecionado
        Component[] components = ((JPanel)((JScrollPane)getContentPane().getComponent(1))
            .getViewport().getView()).getComponents();
        
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                Component[] subComps = ((JPanel) comp).getComponents();
                for (Component sub : subComps) {
                    if (sub instanceof JPanel) {
                        Component[] cards = ((JPanel) sub).getComponents();
                        for (int i = 0; i < cards.length; i++) {
                            if (cards[i] instanceof JPanel) {
                                Component[] cardComps = ((JPanel) cards[i]).getComponents();
                                for (Component c : cardComps) {
                                    if (c instanceof JRadioButton && ((JRadioButton) c).isSelected()) {
                                        config.aplicarTema(Configuracoes.Tema.values()[i]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Aplicar fonte e tamanho
        config.setFontePrincipal((String) cmbFonte.getSelectedItem());
        config.setTamanhoFonteBase(sliderTamanhoFonte.getValue());
        
        // Salvar configurações
        config.salvar();
        
        // Atualizar o hub
        JOptionPane.showMessageDialog(this,
            "✅ Configurações aplicadas!\nReinicie o sistema para ver todas as mudanças.",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
        
        // Recriar o hub
        hub.dispose();
        SwingUtilities.invokeLater(() -> new HubFuncionarios());
    }
}
