package Interacao;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import java.util.Locale;

public class PainelDashboard extends JPanel {
    private GerenciadorFuncionarios gerenciador;
    private Configuracoes config;
    
    public PainelDashboard(GerenciadorFuncionarios gerenciador, Configuracoes config) {
        this.gerenciador = gerenciador;
        this.config = config;
        
        setLayout(new BorderLayout(15, 15));
        setBackground(config.getCorFundoEscuro());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Painel superior com cards de métricas
        add(criarPainelMetricas(), BorderLayout.NORTH);
        
        // Painel central com gráficos e estatísticas
        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 15, 0));
        painelCentral.setBackground(config.getCorFundoEscuro());
        
        painelCentral.add(criarGraficoDistribuicaoCargos());
        painelCentral.add(criarListaFuncionariosRecentes());
        
        add(painelCentral, BorderLayout.CENTER);
        
        // Painel inferior com evolução salarial
        add(criarGraficoEvolucaoSalarial(), BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelMetricas() {
        JPanel painel = new JPanel(new GridLayout(1, 5, 15, 0));
        painel.setBackground(config.getCorFundoEscuro());
        painel.setPreferredSize(new Dimension(0, 140));
        
        List<Funcionario> funcionarios = gerenciador.listarTodos();
        
        // Total de funcionários
        int total = funcionarios.size();
        painel.add(criarCardMetrica("👥", "Total", String.valueOf(total), config.getCorDestaque()));
        
        // Funcionários ativos
        long ativos = funcionarios.stream().filter(f -> "Ativo".equals(f.getStatus())).count();
        painel.add(criarCardMetrica("✅", "Ativos", String.valueOf(ativos), new Color(96, 165, 250)));
        
        // Média salarial
        double mediaSalarial = funcionarios.stream()
            .mapToDouble(f -> f.getHistoricoSalarial().isEmpty() ? 0 : 
                f.getHistoricoSalarial().get(f.getHistoricoSalarial().size() - 1).getSalarioNovo())
            .average().orElse(0);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        painel.add(criarCardMetrica("💰", "Média Salarial", currencyFormat.format(mediaSalarial), 
            new Color(251, 191, 36)));
        
        // Total de advertências
        int totalAdv = funcionarios.stream().mapToInt(f -> f.getAdvertencias().size()).sum();
        painel.add(criarCardMetrica("⚠️", "Advertências", String.valueOf(totalAdv), 
            new Color(248, 113, 113)));
        
        // Ocorrências positivas
        long positivas = funcionarios.stream()
            .flatMap(f -> f.getOcorrencias().stream())
            .filter(o -> "Positiva".equals(o.getTipo()))
            .count();
        painel.add(criarCardMetrica("⭐", "Ocorrências +", String.valueOf(positivas), 
            new Color(52, 211, 153)));
        
        return painel;
    }
    
    private JPanel criarCardMetrica(String emoji, String titulo, String valor, Color cor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(config.getCorCard());
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblEmoji = new JLabel(emoji);
        lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        lblEmoji.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(config.getFonteTitulo());
        lblValor.setForeground(cor);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(config.getFonteNormal());
        lblTitulo.setForeground(config.getCorTextoSecundario());
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(lblEmoji);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);
        card.add(Box.createVerticalStrut(5));
        card.add(lblTitulo);
        
        return card;
    }
    
    private JPanel criarGraficoDistribuicaoCargos() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(config.getCorCard());
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTitulo = new JLabel("📊 Distribuição por Cargo");
        lblTitulo.setFont(config.getFonteSubtitulo());
        lblTitulo.setForeground(config.getCorTextoPrincipal());
        
        painel.add(lblTitulo, BorderLayout.NORTH);
        
        // Contagem de cargos
        Map<String, Integer> distribuicaoCargos = new HashMap<>();
        for (Funcionario f : gerenciador.listarTodos()) {
            String cargo = f.getCargo() != null && !f.getCargo().isEmpty() ? f.getCargo() : "Sem cargo";
            distribuicaoCargos.put(cargo, distribuicaoCargos.getOrDefault(cargo, 0) + 1);
        }
        
        // Painel de barras
        JPanel painelBarras = new JPanel();
        painelBarras.setLayout(new BoxLayout(painelBarras, BoxLayout.Y_AXIS));
        painelBarras.setBackground(config.getCorCard());
        painelBarras.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        Color[] coresCargos = {
            new Color(52, 211, 153),
            new Color(96, 165, 250),
            new Color(168, 85, 247),
            new Color(251, 191, 36),
            new Color(248, 113, 113)
        };
        
        int maxValor = distribuicaoCargos.values().stream().max(Integer::compare).orElse(1);
        int index = 0;
        
        for (Map.Entry<String, Integer> entry : distribuicaoCargos.entrySet()) {
            painelBarras.add(criarBarra(entry.getKey(), entry.getValue(), maxValor, 
                coresCargos[index % coresCargos.length]));
            painelBarras.add(Box.createVerticalStrut(10));
            index++;
        }
        
        JScrollPane scroll = new JScrollPane(painelBarras);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBackground(config.getCorCard());
        
        painel.add(scroll, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarBarra(String label, int valor, int maxValor, Color cor) {
        JPanel painel = new JPanel(new BorderLayout(10, 0));
        painel.setBackground(config.getCorCard());
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(config.getFonteNormal());
        lblLabel.setForeground(config.getCorTextoPrincipal());
        lblLabel.setPreferredSize(new Dimension(150, 0));
        
        JPanel painelBarra = new JPanel(new BorderLayout());
        painelBarra.setBackground(config.getCorFundoEscuro());
        painelBarra.setPreferredSize(new Dimension(0, 30));
        
        int larguraPercent = (int) ((valor * 100.0) / maxValor);
        JPanel barra = new JPanel();
        barra.setBackground(cor);
        barra.setPreferredSize(new Dimension((painelBarra.getWidth() * larguraPercent) / 100, 30));
        
        JLabel lblValor = new JLabel(" " + valor);
        lblValor.setFont(config.getFonteBold());
        lblValor.setForeground(Color.WHITE);
        
        barra.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        barra.add(lblValor);
        
        painelBarra.add(barra, BorderLayout.WEST);
        
        painel.add(lblLabel, BorderLayout.WEST);
        painel.add(painelBarra, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarListaFuncionariosRecentes() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(config.getCorCard());
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTitulo = new JLabel("🆕 Cadastros Recentes");
        lblTitulo.setFont(config.getFonteSubtitulo());
        lblTitulo.setForeground(config.getCorTextoPrincipal());
        
        painel.add(lblTitulo, BorderLayout.NORTH);
        
        // Lista de funcionários
        JPanel painelLista = new JPanel();
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(config.getCorCard());
        painelLista.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        List<Funcionario> funcionarios = gerenciador.listarTodos();
        
        // Pegar os 5 mais recentes (invertendo a lista)
        List<Funcionario> recentes = new ArrayList<>(funcionarios);
        Collections.reverse(recentes);
        
        int count = 0;
        for (Funcionario f : recentes) {
            if (count >= 5) break;
            painelLista.add(criarItemFuncionario(f));
            painelLista.add(Box.createVerticalStrut(8));
            count++;
        }
        
        if (count == 0) {
            JLabel lblVazio = new JLabel("Nenhum funcionário cadastrado");
            lblVazio.setFont(config.getFonteNormal());
            lblVazio.setForeground(config.getCorTextoSecundario());
            painelLista.add(lblVazio);
        }
        
        JScrollPane scroll = new JScrollPane(painelLista);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBackground(config.getCorCard());
        
        painel.add(scroll, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarItemFuncionario(Funcionario f) {
        JPanel item = new JPanel(new BorderLayout(10, 0));
        item.setBackground(config.getCorFundoEscuro());
        item.setBorder(new EmptyBorder(10, 15, 10, 15));
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        // Avatar
        JLabel lblAvatar = new JLabel("👤");
        lblAvatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        
        // Informações
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setBackground(config.getCorFundoEscuro());
        
        JLabel lblNome = new JLabel(f.getNome());
        lblNome.setFont(config.getFonteBold());
        lblNome.setForeground(config.getCorTextoPrincipal());
        
        JLabel lblCargo = new JLabel(f.getCargo() != null ? f.getCargo() : "Sem cargo");
        lblCargo.setFont(config.getFontePequena());
        lblCargo.setForeground(config.getCorTextoSecundario());
        
        painelInfo.add(lblNome);
        painelInfo.add(lblCargo);
        
        // Status
        JLabel lblStatus = new JLabel("Ativo".equals(f.getStatus()) ? "●" : "○");
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblStatus.setForeground("Ativo".equals(f.getStatus()) ? 
            new Color(52, 211, 153) : new Color(148, 163, 184));
        
        item.add(lblAvatar, BorderLayout.WEST);
        item.add(painelInfo, BorderLayout.CENTER);
        item.add(lblStatus, BorderLayout.EAST);
        
        return item;
    }
    
    private JPanel criarGraficoEvolucaoSalarial() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(config.getCorCard());
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(config.getCorBorda(), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        painel.setPreferredSize(new Dimension(0, 200));
        
        JLabel lblTitulo = new JLabel("📈 Evolução Salarial Média (últimos ajustes)");
        lblTitulo.setFont(config.getFonteSubtitulo());
        lblTitulo.setForeground(config.getCorTextoPrincipal());
        
        painel.add(lblTitulo, BorderLayout.NORTH);
        
        // Coletar dados salariais
        List<Double> salarios = new ArrayList<>();
        for (Funcionario f : gerenciador.listarTodos()) {
            List<Funcionario.HistoricoSalarial> historico = f.getHistoricoSalarial();
            if (!historico.isEmpty()) {
                // Pegar até os últimos 6 ajustes de cada funcionário
                int inicio = Math.max(0, historico.size() - 6);
                for (int i = inicio; i < historico.size(); i++) {
                    salarios.add(historico.get(i).getSalarioNovo());
                }
            }
        }
        
        if (!salarios.isEmpty()) {
            painel.add(new GraficoLinhas(salarios, config), BorderLayout.CENTER);
        } else {
            JLabel lblVazio = new JLabel("Sem dados de histórico salarial");
            lblVazio.setFont(config.getFonteNormal());
            lblVazio.setForeground(config.getCorTextoSecundario());
            lblVazio.setHorizontalAlignment(SwingConstants.CENTER);
            painel.add(lblVazio, BorderLayout.CENTER);
        }
        
        return painel;
    }
    
    // Componente para desenhar gráfico de linhas
    private class GraficoLinhas extends JPanel {
        private List<Double> dados;
        private Configuracoes config;
        
        public GraficoLinhas(List<Double> dados, Configuracoes config) {
            this.dados = dados;
            this.config = config;
            setBackground(config.getCorCard());
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int width = getWidth();
            int height = getHeight();
            int padding = 40;
            
            if (dados.isEmpty()) return;
            
            // Encontrar min e max
            double minValor = dados.stream().min(Double::compare).orElse(0.0);
            double maxValor = dados.stream().max(Double::compare).orElse(1.0);
            double range = maxValor - minValor;
            if (range == 0) range = 1;
            
            // Desenhar eixos
            g2d.setColor(config.getCorBorda());
            g2d.drawLine(padding, height - padding, width - padding, height - padding); // Eixo X
            g2d.drawLine(padding, padding, padding, height - padding); // Eixo Y
            
            // Desenhar pontos e linhas
            int pontos = Math.min(dados.size(), 20); // Limitar a 20 pontos
            double xStep = (width - 2 * padding) / (double) Math.max(pontos - 1, 1);
            
            g2d.setColor(config.getCorDestaque());
            g2d.setStroke(new BasicStroke(3));
            
            for (int i = 0; i < pontos - 1; i++) {
                int x1 = padding + (int) (i * xStep);
                int y1 = height - padding - (int) (((dados.get(i) - minValor) / range) * (height - 2 * padding));
                
                int x2 = padding + (int) ((i + 1) * xStep);
                int y2 = height - padding - (int) (((dados.get(i + 1) - minValor) / range) * (height - 2 * padding));
                
                g2d.drawLine(x1, y1, x2, y2);
                
                // Desenhar ponto
                g2d.fillOval(x1 - 4, y1 - 4, 8, 8);
            }
            
            // Último ponto
            if (pontos > 0) {
                int xLast = padding + (int) ((pontos - 1) * xStep);
                int yLast = height - padding - (int) (((dados.get(pontos - 1) - minValor) / range) * (height - 2 * padding));
                g2d.fillOval(xLast - 4, yLast - 4, 8, 8);
            }
            
            // Labels dos valores
            g2d.setFont(config.getFontePequena());
            g2d.setColor(config.getCorTextoSecundario());
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
            g2d.drawString(currencyFormat.format(minValor), 5, height - padding + 5);
            g2d.drawString(currencyFormat.format(maxValor), 5, padding);
        }
    }
}
