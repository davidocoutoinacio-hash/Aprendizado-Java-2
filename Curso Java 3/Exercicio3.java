
import java.util.Scanner;
import java.io.PrintStream;


public class Exercicio3 {

    public static Scanner entr = new Scanner(System.in);
    public static PrintStream impr = System.out;

    public static void main(String[] args) {
        

        impr.println("Qual o tamanho da base do retangulo?");
        int base = entr.nextInt();
        impr.println("Qual o tamanho da altura do retangulo?");
        int altura = entr.nextInt();

        int area = base * altura;

        impr.printf("A área do retângulo de base %d e altura %d é %d\n", base, altura, area);
    
    }

}

/*
Escreva um código que receba a base e a altura de um retângulo, calcule sua área e exiba na tela

fórmula: área = base X altura
*/
