package RPG_SIMPLES;
import java.util.Scanner;

    
class Personagem {
    
 public static void tipos(String[] args) {

    Scanner entrada = new Scanner(System.in);


    System.out.println("Tipos de Personagem:");
    System.out.println("1 - Guerreiro");
    System.out.println("2 - Mago");
    System.out.println("3 - Arqueiro");
    System.out.println("4 - Assassino");
    System.out .println("Digite o número do personagem escolhido:");
    String escolha = entrada.nextLine();

    switch (escolha) {
        case "1":
            System.out.println("Você escolheu Guerreiro!");
            Acoes.main(args);
            break;
        case "2":
            System.out.println("Você escolheu Mago!");
            Acoes.main(args);
            break;
        case "3":
            System.out.println("Você escolheu Arqueiro!");
            Acoes.main(args);
            break;
        case "4":
            System.out.println("Você escolheu Assassino!");
            Acoes.main(args);
            break;
        default:
            System.out.println("Escolha inválida!");
            break;
    }


 }

}