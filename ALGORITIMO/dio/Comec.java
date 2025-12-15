package ALGORITIMO.dio;

import java.util.Scanner;
import java.io.PrintStream;


public class Comec {

    public static PrintStream show = (System.out); 
    public static Scanner scan = new Scanner(System.in);

public static void main(String[] args) {
    
    boolean continuar = true;
    
    while (continuar) {
        show.println("\n╔════════════════════════════════════════╗");
        show.println("║    SISTEMA DE CADASTRO DE USUÁRIOS    ║");
        show.println("╚════════════════════════════════════════╝");
        show.println("1. CADASTRAR USUÁRIO");
        show.println("2. EDITAR USUÁRIO");
        show.println("3. CONSULTAR USUÁRIOS");
        show.println("4. Sair");
        show.print("\nEscolha uma opção: ");
        
        int escolha = scan.nextInt();
        scan.nextLine(); // Limpar buffer
        
        switch (escolha) {
            case 1:
                Cadastrar.usuario();
                break;
            case 2:
                Editar.usuario();
                break;
            case 3:
                Consultar.usuarios();
                break;
            case 4:
                Sair.end();
                continuar = false;
                break;
            default:
                show.println("❌ Opção inválida. Tente novamente.");
                break;
        }
    }
    
    scan.close();
}

}

class Cadastrar {

    public static void usuario() {
        Comec.show.println("\n╔════════════════════════════════════════╗");
        Comec.show.println("║        CADASTRAR NOVO USUÁRIO         ║");
        Comec.show.println("╚════════════════════════════════════════╝");
        
        Comec.show.print("CPF (somente números): ");
        String cpf = Comec.scan.nextLine();
        
        // Verificar se CPF já existe
        if (Usuario.cpfExiste(cpf)) {
            Comec.show.println("\n❌ CPF já cadastrado! Use a opção EDITAR para alterar dados.");
            Comec.show.println("\nPressione Enter para voltar ao menu...");
            Comec.scan.nextLine();
            return;
        }
        
        Comec.show.print("Nome completo: ");
        String nome = Comec.scan.nextLine();
        
        Comec.show.print("Telefone: ");
        String telefone = Comec.scan.nextLine();
        
        Comec.show.print("Email: ");
        String email = Comec.scan.nextLine();
        
        // Criar objeto Usuario e salvar
        Usuario usuario = new Usuario(cpf, nome, telefone, email);
        usuario.salvar();
        
        Comec.show.println("\nPressione Enter para voltar ao menu...");
        Comec.scan.nextLine();
    }

}

class Editar {

    public static void usuario() {
        Comec.show.println("\n╔════════════════════════════════════════╗");
        Comec.show.println("║           EDITAR USUÁRIO              ║");
        Comec.show.println("╚════════════════════════════════════════╝");
        
        Comec.show.println("\nBuscar por:");
        Comec.show.println("1. CPF");
        Comec.show.println("2. Nome");
        Comec.show.println("3. Telefone");
        Comec.show.println("4. Email");
        Comec.show.println("5. Voltar");
        Comec.show.print("Escolha: ");
        
        int opcao = Comec.scan.nextInt();
        Comec.scan.nextLine();
        
        Usuario usuarioEditar = null;
        
        switch (opcao) {
            case 1:
                Comec.show.print("Digite o CPF: ");
                String cpfBusca = Comec.scan.nextLine();
                usuarioEditar = Usuario.buscarPorCpf(cpfBusca);
                if (usuarioEditar == null) {
                    Comec.show.println("❌ Usuário não encontrado!");
                }
                break;
            case 2:
                Comec.show.print("Digite o nome: ");
                String nomeBusca = Comec.scan.nextLine();
                var usuariosPorNome = Usuario.buscarPorNome(nomeBusca);
                usuarioEditar = selecionarDaLista(usuariosPorNome);
                break;
            case 3:
                Comec.show.print("Digite o telefone: ");
                String telBusca = Comec.scan.nextLine();
                var usuariosPorTel = Usuario.buscarPorTelefone(telBusca);
                usuarioEditar = selecionarDaLista(usuariosPorTel);
                break;
            case 4:
                Comec.show.print("Digite o email: ");
                String emailBusca = Comec.scan.nextLine();
                var usuariosPorEmail = Usuario.buscarPorEmail(emailBusca);
                usuarioEditar = selecionarDaLista(usuariosPorEmail);
                break;
            case 5:
                return;
            default:
                Comec.show.println("Opção inválida!");
                return;
        }
        
        if (usuarioEditar != null) {
            usuarioEditar.exibir();
            
            Comec.show.println("\nO que deseja editar?");
            Comec.show.println("1. Nome");
            Comec.show.println("2. Telefone");
            Comec.show.println("3. Email");
            Comec.show.println("4. Editar tudo");
            Comec.show.println("5. Cancelar");
            Comec.show.print("Escolha: ");
            
            int opcaoEdit = Comec.scan.nextInt();
            Comec.scan.nextLine();
            
            switch (opcaoEdit) {
                case 1:
                    Comec.show.print("Novo nome: ");
                    usuarioEditar.setNome(Comec.scan.nextLine());
                    usuarioEditar.atualizar();
                    break;
                case 2:
                    Comec.show.print("Novo telefone: ");
                    usuarioEditar.setTelefone(Comec.scan.nextLine());
                    usuarioEditar.atualizar();
                    break;
                case 3:
                    Comec.show.print("Novo email: ");
                    usuarioEditar.setEmail(Comec.scan.nextLine());
                    usuarioEditar.atualizar();
                    break;
                case 4:
                    Comec.show.print("Novo nome: ");
                    usuarioEditar.setNome(Comec.scan.nextLine());
                    Comec.show.print("Novo telefone: ");
                    usuarioEditar.setTelefone(Comec.scan.nextLine());
                    Comec.show.print("Novo email: ");
                    usuarioEditar.setEmail(Comec.scan.nextLine());
                    usuarioEditar.atualizar();
                    break;
                case 5:
                    return;
                default:
                    Comec.show.println("Opção inválida!");
            }
        }
        
        Comec.show.println("\nPressione Enter para voltar...");
        Comec.scan.nextLine();
    }
    
    private static Usuario selecionarDaLista(java.util.List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            Comec.show.println("❌ Nenhum usuário encontrado!");
            return null;
        }
        
        if (usuarios.size() == 1) {
            return usuarios.get(0);
        }
        
        Comec.show.println("\nVários usuários encontrados:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            Comec.show.println((i + 1) + ". " + u.getNome() + " - CPF: " + u.getCpf());
        }
        
        Comec.show.print("\nEscolha o número do usuário: ");
        int escolha = Comec.scan.nextInt();
        Comec.scan.nextLine();
        
        if (escolha > 0 && escolha <= usuarios.size()) {
            return usuarios.get(escolha - 1);
        }
        
        Comec.show.println("❌ Opção inválida!");
        return null;
    }
}

class Consultar {

    public static void usuarios() {
        Comec.show.println("\n╔════════════════════════════════════════╗");
        Comec.show.println("║         CONSULTAR USUÁRIOS            ║");
        Comec.show.println("╚════════════════════════════════════════╝");
        
        var usuarios = Usuario.listarTodos();
        
        if (usuarios.isEmpty()) {
            Comec.show.println("\n❌ Nenhum usuário cadastrado.");
        } else {
            Comec.show.println("\n📋 Total de usuários: " + usuarios.size());
            Comec.show.println("\n" + "=".repeat(80));
            
            for (Usuario u : usuarios) {
                Comec.show.println("CPF: " + u.getCpf() + " | Nome: " + u.getNome());
                Comec.show.println("Telefone: " + u.getTelefone() + " | Email: " + u.getEmail());
                Comec.show.println("-".repeat(80));
            }
        }
        
        Comec.show.println("\nPressione Enter para voltar ao menu...");
        Comec.scan.nextLine();
    }
}

class Sair {

    public static void end() {
        Comec.show.println("Saindo do programa...");
    }

}


