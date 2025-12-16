
import java.util.Scanner;
import java.io.PrintStream;


public class Exercicio2 {

    public static Scanner entr = new Scanner(System.in);
    public static PrintStream impr = System.out;

    public static void main(String[] args) {
        

        impr.println("Qual o tamanho do lado do quadrado?");
        int lado = entr.nextInt();

        int area = lado * lado;

        impr.printf("A área do quadrado de lado %d é %d\n", lado, area);

    
    }

}



//Escreva um código que receba o tamanho do lado de um quadrado, calcule sua área e exiba na tela fórmula: área = lado X lado

