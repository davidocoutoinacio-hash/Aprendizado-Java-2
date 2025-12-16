
import java.util.Scanner;
import java.io.PrintStream;


public class Exercicio1 {

    public static Scanner entr = new Scanner(System.in);
    public static PrintStream impr = System.out;

    public static void main(String[] args) {
        

        impr.println("Qual seu nome?");
        String nome = entr.nextLine();
        impr.println("Qual sua idade?");
        int idade = entr.nextInt();

        impr.printf("Seja bem vindo, %s!, sua idade é %d anos\n", nome, idade); 

        


    }

}
