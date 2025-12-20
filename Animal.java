 import java.util.Scanner;
 
 class Animal {


    String nome;
    Scanner scanner = new Scanner(System.in);

    void voz() {
        System.out.println("Escolha um animal (cachorro, gato, vaca, humano): ");
        nome = scanner.nextLine();
        switch (nome) {
            case "cachorro":
                System.out.println("Au Au");
                System.out.println("Um segredo...");
                System.out.println("Escolha um numero de 1 a 3...");
                int numero = scanner.nextInt();
                if (numero >= 1 && numero <= 3) {
                    System.out.println("Parabéns! Você descobriu o segredo do cachorro!");
                        switch (numero) {
                            case 1:
                                System.out.println("O cachorro gosta de brincar!");
                                System.out.println("Você perdeu!");
                                break;
                            case 2:
                                System.out.println("O cachorro gosta de correr!");
                                System.out.println("Escolha outro numero de 1 a 3...");
                                numero = scanner.nextInt();
                                switch (numero) {
                                    case 1:
                                        System.out.println("O cachorro gosta de brincar!");
                                        System.out.println("Você perdeu!");
                                        break;
                                    case 2:
                                        System.out.println("O cachorro gosta de correr!");
                                        System.out.println("Vamos continuar!");
                                            System.out.println("Escolha outro numero de 1 a 3...");
                                            numero = scanner.nextInt();
                                            switch (numero) {
                                                case 1:
                                                    System.out.println("O cachorro gosta de brincar!");
                                                    System.out.println("Você perdeu!");
                                                    break;
                                                case 2:
                                                    System.out.println("O cachorro gosta de correr!");
                                                    System.out.println("Você perdeu!");
                                                    numero = scanner.nextInt();
                                                    switch (numero) {
                                                        case 1:
                                                            System.out.println("O cachorro gosta de brincar!");
                                                            System.out.println("Você perdeu!");
                                                            numero = scanner.nextInt();
                                                            switch (numero) {
                                                                case 1:
                                                                    System.out.println("O cachorro gosta de brincar!");
                                                                    System.out.println("Você perdeu!");
                                                                    break;
                                                                case 2:
                                                                    System.out.println("Acabou...!");
                                                                    
                                                                    break;
                                                                case 3:
                                                                    System.out.println("O cachorro gosta de nadar!");
                                                                    System.out.println("Você perdeu!");
                                                                    numero = scanner.nextInt();
                                                                    switch (numero) {
                                                                        case 1:
                                                                            System.out.println("O cachorro gosta de brincar!");
                                                                            System.out.println("Você perdeu!");
                                                                            break;
                                                                        case 2:
                                                                            System.out.println("Acabou...!");
                                                                            
                                                                            break;
                                                                        case 3:
                                                                            System.out.println("O cachorro gosta de nadar!");
                                                                            System.out.println("Você perdeu!");
                                                                            break;
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                        case 2:
                                                            System.out.println("Acabou...!");
                                                            
                                                            break;
                                                        case 3:
                                                            System.out.println("O cachorro gosta de nadar!");
                                                            System.out.println("Você perdeu!");
                                                            break;
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("O cachorro gosta de nadar!");
                                                    System.out.println("Mais uma vez, escolha um numero de 1 a 3...");
                                                    numero = scanner.nextInt();
                                                    switch (numero) {
                                                        case 1:
                                                            System.out.println("O cachorro gosta de brincar!");
                                                            System.out.println("Você perdeu!");
                                                            break;
                                                        case 2:
                                                            System.out.println("Acabou...!");
                                                            
                                                            break;
                                                        case 3:
                                                            System.out.println("O cachorro gosta de nadar!");
                                                            System.out.println("Você perdeu!");
                                                            break;
                                                    }
                                                    break;
                                            }
                                        break;
                                    case 3:
                                        System.out.println("O cachorro gosta de nadar!");
                                        System.out.println("Você perdeu!");
                                        break;
                                }

                                break;
                            case 3:
                                System.out.println("O cachorro gosta de nadar!");
                                System.out.println("Você perdeu!");
                                break;
                        }
                } else {
                    System.out.println("Tente novamente para descobrir o segredo!");
                }
                
                break;
            case "gato":
                System.out.println("Miau");
                System.out.println("Você perdeu!");
                break;
            case "vaca":
                System.out.println("Muu");
                System.out.println("Você perdeu!");
                break;
            case "humano":
                System.out.println("Olá");
                System.out.println("Você perdeu!");
                break;

            default:
                System.out.println("Som desconhecido");
                System.out.println("Você perdeu!");
                break;
        }
    }

    void correr() {
        System.out.println("Correndo");
        System.out.println("Você perdeu!");
    }

    void dormir() {
        System.out.println("Dormindo");
        System.out.println("Você perdeu!");
    }

    void comer() {
        System.out.println("Comendo");
        System.out.println("Você perdeu!");
    }

    void brincar() {
        System.out.println("Brincando");
        System.out.println("Você perdeu!");
    }

    void nadar() {
        System.out.println("Nadando");
        System.out.println("Você perdeu!");
    }

    void andar() {
        System.out.println("Andando");
        System.out.println("Você perdeu!");
    }
    
}
