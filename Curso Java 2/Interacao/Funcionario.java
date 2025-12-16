package Interacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Dados básicos
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    
    // Dados profissionais
    private String cargo;
    private String descricaoCargo;
    private double salarioAtual;
    private String departamento;
    private Date dataAdmissao;
    private String status; // Ativo, Férias, Afastado, Demitido
    
    // Dados adicionais
    private String fotoCaminho;
    private String endereco;
    private String dataNascimento;
    private String rg;
    
    // Históricos
    private List<HistoricoSalarial> historicoSalarial;
    private List<Ocorrencia> ocorrencias;
    private List<Advertencia> advertencias;
    
    public Funcionario(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.historicoSalarial = new ArrayList<>();
        this.ocorrencias = new ArrayList<>();
        this.advertencias = new ArrayList<>();
        this.status = "Ativo";
        this.dataAdmissao = new Date();
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    
    public String getDescricaoCargo() { return descricaoCargo; }
    public void setDescricaoCargo(String descricaoCargo) { this.descricaoCargo = descricaoCargo; }
    
    public double getSalarioAtual() { return salarioAtual; }
    public void setSalarioAtual(double salarioAtual) { this.salarioAtual = salarioAtual; }
    
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    
    public Date getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(Date dataAdmissao) { this.dataAdmissao = dataAdmissao; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getFotoCaminho() { return fotoCaminho; }
    public void setFotoCaminho(String fotoCaminho) { this.fotoCaminho = fotoCaminho; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    
    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }
    
    public List<HistoricoSalarial> getHistoricoSalarial() { return historicoSalarial; }
    public List<Ocorrencia> getOcorrencias() { return ocorrencias; }
    public List<Advertencia> getAdvertencias() { return advertencias; }
    
    public void adicionarHistoricoSalarial(HistoricoSalarial historico) {
        this.historicoSalarial.add(historico);
    }
    
    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencias.add(ocorrencia);
    }
    
    public void adicionarAdvertencia(Advertencia advertencia) {
        this.advertencias.add(advertencia);
    }
    
    // Classes internas para históricos
    public static class HistoricoSalarial implements Serializable {
        private static final long serialVersionUID = 1L;
        private Date data;
        private double salarioAnterior;
        private double salarioNovo;
        private String motivo;
        
        public HistoricoSalarial(double salarioAnterior, double salarioNovo, String motivo) {
            this.data = new Date();
            this.salarioAnterior = salarioAnterior;
            this.salarioNovo = salarioNovo;
            this.motivo = motivo;
        }
        
        public Date getData() { return data; }
        public double getSalarioAnterior() { return salarioAnterior; }
        public double getSalarioNovo() { return salarioNovo; }
        public String getMotivo() { return motivo; }
    }
    
    public static class Ocorrencia implements Serializable {
        private static final long serialVersionUID = 1L;
        private Date data;
        private String tipo; // Positiva, Neutra, Negativa
        private String descricao;
        private String registradoPor;
        
        public Ocorrencia(String tipo, String descricao, String registradoPor) {
            this.data = new Date();
            this.tipo = tipo;
            this.descricao = descricao;
            this.registradoPor = registradoPor;
        }
        
        public Date getData() { return data; }
        public String getTipo() { return tipo; }
        public String getDescricao() { return descricao; }
        public String getRegistradoPor() { return registradoPor; }
    }
    
    public static class Advertencia implements Serializable {
        private static final long serialVersionUID = 1L;
        private Date data;
        private String gravidade; // Leve, Média, Grave
        private String motivo;
        private String descricao;
        private String aplicadaPor;
        
        public Advertencia(String gravidade, String motivo, String descricao, String aplicadaPor) {
            this.data = new Date();
            this.gravidade = gravidade;
            this.motivo = motivo;
            this.descricao = descricao;
            this.aplicadaPor = aplicadaPor;
        }
        
        public Date getData() { return data; }
        public String getGravidade() { return gravidade; }
        public String getMotivo() { return motivo; }
        public String getDescricao() { return descricao; }
        public String getAplicadaPor() { return aplicadaPor; }
    }
}
