package ALGORITIMO.dio;

import java.io.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private static final String ARQUIVO_CONFIG = "ALGORITIMO/dio/config.properties";
    
    // Construtor com parâmetros
    public Usuario(String cpf, String nome, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    
    // Construtor vazio
    public Usuario() {
        this.cpf = "";
        this.nome = "";
        this.telefone = "";
        this.email = "";
    }
    
    // Verificar se CPF já existe
    public static boolean cpfExiste(String cpf) {
        try {
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            if (!arquivo.exists()) {
                return false;
            }
            
            FileInputStream input = new FileInputStream(arquivo);
            props.load(input);
            input.close();
            
            return props.containsKey(cpf + ".nome");
            
        } catch (IOException e) {
            return false;
        }
    }
    
    // Salvar usuário no arquivo
    public boolean salvar() {
        try {
            // Verificar se CPF já existe
            if (cpfExiste(this.cpf)) {
                Comec.show.println("✗ Erro: CPF já cadastrado!");
                return false;
            }
            
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            // Se o arquivo já existe, carrega as propriedades existentes
            if (arquivo.exists()) {
                FileInputStream input = new FileInputStream(arquivo);
                props.load(input);
                input.close();
            }
            
            // Adiciona o novo usuário usando CPF como chave única
            props.setProperty(cpf + ".nome", nome);
            props.setProperty(cpf + ".telefone", telefone);
            props.setProperty(cpf + ".email", email);
            
            // Salva no arquivo
            FileOutputStream output = new FileOutputStream(arquivo);
            props.store(output, "Sistema de Cadastro - Usuários");
            output.close();
            
            Comec.show.println("✓ Usuário cadastrado com sucesso!");
            return true;
            
        } catch (IOException e) {
            Comec.show.println("✗ Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }
    
    // Atualizar usuário existente
    public boolean atualizar() {
        try {
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            if (!arquivo.exists()) {
                Comec.show.println("✗ Nenhum usuário cadastrado.");
                return false;
            }
            
            FileInputStream input = new FileInputStream(arquivo);
            props.load(input);
            input.close();
            
            if (!props.containsKey(cpf + ".nome")) {
                Comec.show.println("✗ Usuário não encontrado!");
                return false;
            }
            
            // Atualiza os dados
            props.setProperty(cpf + ".nome", nome);
            props.setProperty(cpf + ".telefone", telefone);
            props.setProperty(cpf + ".email", email);
            
            // Salva no arquivo
            FileOutputStream output = new FileOutputStream(arquivo);
            props.store(output, "Sistema de Cadastro - Usuários");
            output.close();
            
            Comec.show.println("✓ Usuário atualizado com sucesso!");
            return true;
            
        } catch (IOException e) {
            Comec.show.println("✗ Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }
    
    // Buscar usuário por CPF
    public static Usuario buscarPorCpf(String cpf) {
        try {
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            if (!arquivo.exists()) {
                return null;
            }
            
            FileInputStream input = new FileInputStream(arquivo);
            props.load(input);
            input.close();
            
            String nome = props.getProperty(cpf + ".nome");
            if (nome == null) {
                return null;
            }
            
            String telefone = props.getProperty(cpf + ".telefone", "");
            String email = props.getProperty(cpf + ".email", "");
            
            return new Usuario(cpf, nome, telefone, email);
            
        } catch (IOException e) {
            return null;
        }
    }
    
    // Buscar usuários por nome (busca parcial)
    public static List<Usuario> buscarPorNome(String nomeBusca) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            if (!arquivo.exists()) {
                return usuarios;
            }
            
            FileInputStream input = new FileInputStream(arquivo);
            props.load(input);
            input.close();
            
            // Percorre todas as propriedades
            for (String key : props.stringPropertyNames()) {
                if (key.endsWith(".nome")) {
                    String nome = props.getProperty(key);
                    if (nome.toLowerCase().contains(nomeBusca.toLowerCase())) {
                        String cpf = key.replace(".nome", "");
                        String telefone = props.getProperty(cpf + ".telefone", "");
                        String email = props.getProperty(cpf + ".email", "");
                        usuarios.add(new Usuario(cpf, nome, telefone, email));
                    }
                }
            }
            
        } catch (IOException e) {
            Comec.show.println("✗ Erro ao buscar: " + e.getMessage());
        }
        return usuarios;
    }
    
    // Buscar usuários por telefone
    public static List<Usuario> buscarPorTelefone(String telefoneBusca) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            if (!arquivo.exists()) {
                return usuarios;
            }
            
            FileInputStream input = new FileInputStream(arquivo);
            props.load(input);
            input.close();
            
            for (String key : props.stringPropertyNames()) {
                if (key.endsWith(".telefone")) {
                    String telefone = props.getProperty(key);
                    if (telefone.contains(telefoneBusca)) {
                        String cpf = key.replace(".telefone", "");
                        String nome = props.getProperty(cpf + ".nome", "");
                        String email = props.getProperty(cpf + ".email", "");
                        usuarios.add(new Usuario(cpf, nome, telefone, email));
                    }
                }
            }
            
        } catch (IOException e) {
            Comec.show.println("✗ Erro ao buscar: " + e.getMessage());
        }
        return usuarios;
    }
    
    // Buscar usuários por email
    public static List<Usuario> buscarPorEmail(String emailBusca) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            if (!arquivo.exists()) {
                return usuarios;
            }
            
            FileInputStream input = new FileInputStream(arquivo);
            props.load(input);
            input.close();
            
            for (String key : props.stringPropertyNames()) {
                if (key.endsWith(".email")) {
                    String email = props.getProperty(key);
                    if (email.toLowerCase().contains(emailBusca.toLowerCase())) {
                        String cpf = key.replace(".email", "");
                        String nome = props.getProperty(cpf + ".nome", "");
                        String telefone = props.getProperty(cpf + ".telefone", "");
                        usuarios.add(new Usuario(cpf, nome, telefone, email));
                    }
                }
            }
            
        } catch (IOException e) {
            Comec.show.println("✗ Erro ao buscar: " + e.getMessage());
        }
        return usuarios;
    }
    
    // Listar todos os usuários
    public static List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Properties props = new Properties();
            File arquivo = new File(ARQUIVO_CONFIG);
            
            if (!arquivo.exists()) {
                Comec.show.println("Nenhum usuário cadastrado.");
                return usuarios;
            }
            
            FileInputStream input = new FileInputStream(arquivo);
            props.load(input);
            input.close();
            
            // Percorre todas as propriedades e agrupa por CPF
            for (String key : props.stringPropertyNames()) {
                if (key.endsWith(".nome")) {
                    String cpf = key.replace(".nome", "");
                    String nome = props.getProperty(key);
                    String telefone = props.getProperty(cpf + ".telefone", "");
                    String email = props.getProperty(cpf + ".email", "");
                    usuarios.add(new Usuario(cpf, nome, telefone, email));
                }
            }
            
        } catch (IOException e) {
            Comec.show.println("✗ Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }
    
    // Exibir dados do usuário
    public void exibir() {
        Comec.show.println("\n╔════════════════════════════════════════════════╗");
        Comec.show.println("║           DADOS DO USUÁRIO                    ║");
        Comec.show.println("╚════════════════════════════════════════════════╝");
        Comec.show.println("CPF: " + cpf);
        Comec.show.println("Nome: " + nome);
        Comec.show.println("Telefone: " + telefone);
        Comec.show.println("Email: " + email);
        Comec.show.println("════════════════════════════════════════════════\n");
    }
    
    // Getters
    public String getCpf() {
        return cpf;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Setters
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}