
import java.util.Scanner;
import java.io.PrintStream;


public class Exercicio4 {

    public static Scanner entr = new Scanner(System.in);
    public static PrintStream impr = System.out;

    public static void main(String[] args) {

        impr.println("Qual nome da primeira pessoa?");
        String nome = entr.nextLine();
        impr.println("Qual nome da segunda pessoa?");
        String nome2 = entr.nextLine();
        impr.println("Qual a idade da primeira pessoa?");
        int idade = entr.nextInt();
        impr.println("Qual a idade da segunda pessoa?");
        int idade2 = entr.nextInt();

        impr.printf("a diferenca de idade entre %s e %s é %d anos\n", nome, nome2, Math.abs(idade - idade2)); 

    }

}

//Escreva um código que receba o nome e a idade de 2 pessoas e imprima a diferença de idade entre elas