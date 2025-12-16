package Interacao;

import java.awt.Color;
import java.awt.Font;
import java.io.*;

public class Configuracoes implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ARQUIVO_CONFIG = "configuracoes.dat";
    
    // Temas disponíveis
    public enum Tema {
        ESCURO, CLARO, AZUL, ROXO, VERDE
    }
    
    private Tema temaAtual;
    private String fontePrincipal;
    private int tamanhoFonteBase;
    
    // Cores do tema atual
    private Color corFundoEscuro;
    private Color corCard;
    private Color corCardHover;
    private Color corDestaque;
    private Color corInfo;
    private Color corAviso;
    private Color corPerigo;
    private Color corTextoPrincipal;
    private Color corTextoSecundario;
    private Color corBorda;
    
    public Configuracoes() {
        // Valores padrão
        this.temaAtual = Tema.ESCURO;
        this.fontePrincipal = "Segoe UI";
        this.tamanhoFonteBase = 13;
        aplicarTema(Tema.ESCURO);
    }
    
    public void aplicarTema(Tema tema) {
        this.temaAtual = tema;
        
        switch (tema) {
            case ESCURO:
                corFundoEscuro = new Color(23, 32, 42);
                corCard = new Color(32, 44, 57);
                corCardHover = new Color(41, 55, 72);
                corDestaque = new Color(52, 211, 153);
                corInfo = new Color(96, 165, 250);
                corAviso = new Color(251, 191, 36);
                corPerigo = new Color(248, 113, 113);
                corTextoPrincipal = new Color(241, 245, 249);
                corTextoSecundario = new Color(148, 163, 184);
                corBorda = new Color(51, 65, 85);
                break;
                
            case CLARO:
                corFundoEscuro = new Color(243, 244, 246);
                corCard = new Color(255, 255, 255);
                corCardHover = new Color(249, 250, 251);
                corDestaque = new Color(16, 185, 129);
                corInfo = new Color(59, 130, 246);
                corAviso = new Color(245, 158, 11);
                corPerigo = new Color(239, 68, 68);
                corTextoPrincipal = new Color(17, 24, 39);
                corTextoSecundario = new Color(107, 114, 128);
                corBorda = new Color(229, 231, 235);
                break;
                
            case AZUL:
                corFundoEscuro = new Color(15, 23, 42);
                corCard = new Color(30, 41, 59);
                corCardHover = new Color(51, 65, 85);
                corDestaque = new Color(56, 189, 248);
                corInfo = new Color(147, 197, 253);
                corAviso = new Color(251, 191, 36);
                corPerigo = new Color(248, 113, 113);
                corTextoPrincipal = new Color(248, 250, 252);
                corTextoSecundario = new Color(148, 163, 184);
                corBorda = new Color(71, 85, 105);
                break;
                
            case ROXO:
                corFundoEscuro = new Color(24, 24, 27);
                corCard = new Color(39, 39, 42);
                corCardHover = new Color(63, 63, 70);
                corDestaque = new Color(168, 85, 247);
                corInfo = new Color(192, 132, 252);
                corAviso = new Color(251, 191, 36);
                corPerigo = new Color(248, 113, 113);
                corTextoPrincipal = new Color(250, 250, 250);
                corTextoSecundario = new Color(161, 161, 170);
                corBorda = new Color(82, 82, 91);
                break;
                
            case VERDE:
                corFundoEscuro = new Color(20, 29, 32);
                corCard = new Color(30, 41, 44);
                corCardHover = new Color(44, 57, 61);
                corDestaque = new Color(52, 211, 153);
                corInfo = new Color(45, 212, 191);
                corAviso = new Color(251, 191, 36);
                corPerigo = new Color(248, 113, 113);
                corTextoPrincipal = new Color(236, 253, 245);
                corTextoSecundario = new Color(134, 239, 172);
                corBorda = new Color(52, 73, 85);
                break;
        }
    }
    
    // Getters
    public Tema getTemaAtual() { return temaAtual; }
    public String getFontePrincipal() { return fontePrincipal; }
    public int getTamanhoFonteBase() { return tamanhoFonteBase; }
    
    public Color getCorFundoEscuro() { return corFundoEscuro; }
    public Color getCorCard() { return corCard; }
    public Color getCorCardHover() { return corCardHover; }
    public Color getCorDestaque() { return corDestaque; }
    public Color getCorInfo() { return corInfo; }
    public Color getCorAviso() { return corAviso; }
    public Color getCorPerigo() { return corPerigo; }
    public Color getCorTextoPrincipal() { return corTextoPrincipal; }
    public Color getCorTextoSecundario() { return corTextoSecundario; }
    public Color getCorBorda() { return corBorda; }
    
    // Setters
    public void setFontePrincipal(String fontePrincipal) { 
        this.fontePrincipal = fontePrincipal; 
    }
    
    public void setTamanhoFonteBase(int tamanhoFonteBase) { 
        this.tamanhoFonteBase = tamanhoFonteBase; 
    }
    
    // Métodos auxiliares para fontes
    public Font getFonteTitulo() {
        return new Font(fontePrincipal, Font.BOLD, tamanhoFonteBase + 15);
    }
    
    public Font getFonteSubtitulo() {
        return new Font(fontePrincipal, Font.BOLD, tamanhoFonteBase + 7);
    }
    
    public Font getFonteNormal() {
        return new Font(fontePrincipal, Font.PLAIN, tamanhoFonteBase);
    }
    
    public Font getFonteBold() {
        return new Font(fontePrincipal, Font.BOLD, tamanhoFonteBase);
    }
    
    public Font getFontePequena() {
        return new Font(fontePrincipal, Font.PLAIN, tamanhoFonteBase - 2);
    }
    
    // Persistência
    public void salvar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CONFIG))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Erro ao salvar configurações: " + e.getMessage());
        }
    }
    
    public static Configuracoes carregar() {
        File arquivo = new File(ARQUIVO_CONFIG);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_CONFIG))) {
                return (Configuracoes) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar configurações: " + e.getMessage());
            }
        }
        return new Configuracoes();
    }
}
