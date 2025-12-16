package Interacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorFuncionarios {
    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.dat";
    private List<Funcionario> funcionarios;
    
    public GerenciadorFuncionarios() {
        funcionarios = new ArrayList<>();
        carregarDados();
    }
    
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarDados();
    }
    
    public void removerFuncionario(String cpf) {
        funcionarios.removeIf(f -> f.getCpf().equals(cpf));
        salvarDados();
    }
    
    public Funcionario buscarPorCpf(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }
    
    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }
    
    public void atualizarFuncionario(Funcionario funcionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCpf().equals(funcionario.getCpf())) {
                funcionarios.set(i, funcionario);
                salvarDados();
                return;
            }
        }
    }
    
    public boolean cpfJaCadastrado(String cpf) {
        return funcionarios.stream().anyMatch(f -> f.getCpf().equals(cpf));
    }
    
    public boolean emailJaCadastrado(String email) {
        return funcionarios.stream().anyMatch(f -> f.getEmail().equalsIgnoreCase(email));
    }
    
    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_FUNCIONARIOS))) {
            oos.writeObject(funcionarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO_FUNCIONARIOS);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_FUNCIONARIOS))) {
                funcionarios = (List<Funcionario>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                funcionarios = new ArrayList<>();
                System.err.println("Erro ao carregar dados: " + e.getMessage());
            }
        }
    }
    
    // Migrar dados antigos de Pessoa para Funcionario
    public void migrarDePessoas(List<Object> pessoas) {
        for (Object obj : pessoas) {
            try {
                // Usar reflection para pegar os dados da classe Pessoa antiga
                String nome = (String) obj.getClass().getMethod("getNome").invoke(obj);
                String cpf = (String) obj.getClass().getMethod("getCpf").invoke(obj);
                String email = (String) obj.getClass().getMethod("getEmail").invoke(obj);
                String telefone = (String) obj.getClass().getMethod("getTelefone").invoke(obj);
                
                if (!cpfJaCadastrado(cpf)) {
                    Funcionario funcionario = new Funcionario(nome, cpf, email, telefone);
                    funcionarios.add(funcionario);
                }
            } catch (Exception e) {
                System.err.println("Erro ao migrar pessoa: " + e.getMessage());
            }
        }
        salvarDados();
    }
}
