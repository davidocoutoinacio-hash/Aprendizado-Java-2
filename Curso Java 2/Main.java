import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Pessoa(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nCPF: " + cpf + "\nEmail: " + email + "\nTelefone: " + telefone;
    }
}

public class Main {
    private static List<Pessoa> cadastros = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String ARQUIVO_DADOS = "cadastros.dat";

    public static void main(String[] args) {
        // Carregar dados salvos ao iniciar
        carregarDados();
        
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    listarCadastros();
                    break;
                case 3:
                    buscarPorCpf();
                    break;
                case 4:
                    excluirCadastro();
                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== SISTEMA DE CADASTRO =====");
        System.out.println("1 - Cadastrar nova pessoa");
        System.out.println("2 - Listar todos os cadastros");
        System.out.println("3 - Buscar por CPF");
        System.out.println("4 - Excluir cadastro");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarPessoa() {
        System.out.println("\n===== NOVO CADASTRO =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        // Validação de CPF único
        String cpf;
        while (true) {
            System.out.print("CPF: ");
            cpf = scanner.nextLine();

            if (cpfJaCadastrado(cpf)) {
                System.out.println("❌ ERRO: Este CPF já está cadastrado no sistema!");
                System.out.print("Deseja tentar outro CPF? (s/n): ");
                String resposta = scanner.nextLine();
                if (!resposta.equalsIgnoreCase("s")) {
                    System.out.println("Cadastro cancelado.");
                    return;
                }
            } else {
                break;
            }
        }

        // Validação de Email único
        String email;
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();

            if (emailJaCadastrado(email)) {
                System.out.println("❌ ERRO: Este email já está cadastrado no sistema!");
                System.out.print("Deseja tentar outro email? (s/n): ");
                String resposta = scanner.nextLine();
                if (!resposta.equalsIgnoreCase("s")) {
                    System.out.println("Cadastro cancelado.");
                    return;
                }
            } else {
                break;
            }
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        // Confirmação final antes de cadastrar
        System.out.println("\n----- CONFIRME OS DADOS -----");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
        System.out.print("\nConfirmar cadastro? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            Pessoa novaPessoa = new Pessoa(nome, cpf, email, telefone);
            cadastros.add(novaPessoa);
            salvarDados();
            System.out.println("✅ Cadastro realizado com sucesso!");
        } else {
            System.out.println("Cadastro cancelado.");
        }
    }

    private static boolean cpfJaCadastrado(String cpf) {
        for (Pessoa pessoa : cadastros) {
            if (pessoa.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private static boolean emailJaCadastrado(String email) {
        for (Pessoa pessoa : cadastros) {
            if (pessoa.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private static void listarCadastros() {
        if (cadastros.isEmpty()) {
            System.out.println("\nNenhum cadastro encontrado.");
        } else {
            System.out.println("\n===== CADASTROS REALIZADOS =====");
            for (int i = 0; i < cadastros.size(); i++) {
                System.out.println("\n--- Cadastro " + (i + 1) + " ---");
                System.out.println(cadastros.get(i));
            }
            System.out.println("\nTotal de cadastros: " + cadastros.size());
        }
    }

    private static void buscarPorCpf() {
        System.out.println("\n===== BUSCAR POR CPF =====");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        boolean encontrado = false;
        for (Pessoa pessoa : cadastros) {
            if (pessoa.getCpf().equals(cpf)) {
                System.out.println("\n✅ Cadastro encontrado:");
                System.out.println(pessoa);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("❌ Nenhum cadastro encontrado com este CPF.");
        }
    }

    private static void excluirCadastro() {
        System.out.println("\n===== EXCLUIR CADASTRO =====");
        System.out.print("Digite o CPF do cadastro a excluir: ");
        String cpf = scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < cadastros.size(); i++) {
            if (cadastros.get(i).getCpf().equals(cpf)) {
                System.out.println("\nCadastro encontrado:");
                System.out.println(cadastros.get(i));
                System.out.print("\nConfirmar exclusão? (s/n): ");
                String confirmacao = scanner.nextLine();

                if (confirmacao.equalsIgnoreCase("s")) {
                    cadastros.remove(i);
                    salvarDados();
                    System.out.println("✅ Cadastro excluído com sucesso!");
                } else {
                    System.out.println("Exclusão cancelada.");
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("❌ Nenhum cadastro encontrado com este CPF.");
        }
    }

    private static void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(cadastros);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregarDados() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
                cadastros = (List<Pessoa>) ois.readObject();
                System.out.println("✅ " + cadastros.size() + " cadastro(s) carregado(s) do banco de dados.\n");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("⚠️ Erro ao carregar dados: " + e.getMessage());
                cadastros = new ArrayList<>();
            }
        } else {
            System.out.println("📁 Nenhum banco de dados encontrado. Iniciando sistema vazio.\n");
        }
    }
}
