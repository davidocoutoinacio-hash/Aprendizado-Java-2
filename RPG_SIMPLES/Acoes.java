package RPG_SIMPLES;
import java.util.Scanner;


class Acoes {
    
 public static void main(String[] args) {

         Scanner entrada = new Scanner(System.in);

        System.out.println("Escolha uma ação:");
        System.out.println("1 - Ataque");
        System.out.println("2 - Defesa");
        System.out.println("3 - Fugir");
        System.out.println("4 - Desviar");
        var escolha = entrada.nextInt();
        
        
         switch (escolha) {
        case 1:
            System.out.println("Ataque");
                System.out.println("Escolha o tipo de ataque:");
                System.out.println("1 - Ataque Fraco");
                System.out.println("2 - Ataque Médio");
                System.out.println("3 - Ataque Forte");
                System.out.println("4 - Ataque Especial");
                 System.out.println("5 - Voltar");
                escolha = entrada.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Ataque Fraco");
                    Acoes.main(args); 
                    break;
                case 2:
                    System.out.println("Ataque Médio");
                    Acoes.main(args);
                    break;
                case 3:
                    System.out.println("Ataque Forte");
                    Acoes.main(args);
                    break;
                case 4:
                    System.out.println("Ataque Especial");
                    Acoes.main(args);
                    break;
                case 5:
                    System.out.println("voltar");
                    App.main(args);
                    break;
            }   
        case 2:
            System.out.println("Defesa");
                System.out.println("Escolha o tipo de defesa:");
                System.out.println("1 - Defesa Fraca");
                System.out.println("2 - Defesa Média");
                System.out.println("3 - Defesa Forte");
                System.out.println("4 - Defesa Especial");
                System.out.println("5 - Voltar");
                escolha = entrada.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Defesa Fraca");
                    Acoes.main(args); 
                    break;
                case 2:
                    System.out.println("Defesa Média");
                    Acoes.main(args);
                    break;
                case 3:
                    System.out.println("Defesa Forte");
                    Acoes.main(args);
                    break;
                case 4:
                    System.out.println("Defesa Especial");
                    Acoes.main(args);
                    break;    
                case 5:
                    System.out.println("voltar");
                    App.main(args);
                    break;
            }
            
            
        case 3:
            System.out.println("Fugir");
            System.out.println("Escolha a direção para fugir:");
            System.out.println("1 - Esquerda");
            System.out.println("2 - Direita");
            System.out.println("3 - Frente");
            System.out.println("4 - Trás");
            System.out.println("5 - Voltar");

            escolha = entrada.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Fugir para esquerda");
                    Acoes.main(args); 
                    break;
                case 2:
                    System.out.println("Fugir para direita");
                    Acoes.main(args);
                    break;
                case 3:
                    System.out.println("Fugir para frente");
                    Acoes.main(args);
                    break;
                case 4:
                    System.out.println("Fugir para trás");
                    Acoes.main(args);
                    break;
                case 5:
                    System.out.println("voltar");
                    App.main(args);
                    break;
            }
            
        case 4:
            System.out.println("Desviar");
            System.out.println("Escolha a direção para desviar:");
            System.out.println("1 - Esquerda");
            System.out.println("2 - Direita");
            System.out.println("3 - Frente");
            System.out.println("4 - Trás");
            System.out.println("5 - Voltar");
            escolha = entrada.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Desviar para esquerda");
                    Acoes.main(args); 
                    break;
                case 2:
                    System.out.println("Desviar para direita");
                    Acoes.main(args);
                    break;
                case 3:
                    System.out.println("Desviar para frente");
                    Acoes.main(args);
                    break;
                case 4:
                    System.out.println("Desviar para trás");
                    Acoes.main(args);
                    break;
                case 5:
                    System.out.println("voltar");
                    App.main(args);
                    break;
            }
          

         default:
            System.out.println("Nenhuma ação escolhida");
            Acoes.main(args);
            break;
    }


    }

}