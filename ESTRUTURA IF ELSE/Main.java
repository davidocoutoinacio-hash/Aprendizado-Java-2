import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        System.out.println("Informe seu nome:");
        var name = scanner.nextLine();
        System.out.println("Informe sua idade:");
        var age = scanner.nextInt();
        System.out.println("Voce é emancipado? Sim ou não:");
        var isEmancipated = scanner.next().equalsIgnoreCase("s");

        if  (age >= 18){
         System.out.printf("%s você tem %s anos e pode dirigir \n", name, age);
        } else if (age >= 16 && isEmancipated) {
         System.out.printf("%s você é emancipado e pode dirigir \n", name);
        } else {
         System.out.printf("%s você não pode dirigir \n", name);
     
    }

    }
}
  
    
    

