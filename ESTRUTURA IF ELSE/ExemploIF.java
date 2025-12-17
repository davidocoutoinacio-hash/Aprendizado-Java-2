import java.util.Scanner;

public class ExemploIF {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        System.out.println("Informe seu nome:");
        var name = scanner.nextLine();
        System.out.println("Informe sua idade:");
        var age = scanner.nextInt();
        System.out.println("Voce é emancipado? Sim ou não:");
        var isEmancipated = scanner.next().equalsIgnoreCase("s");

        var canDrive = (age >= 18) || (age >= 16 && isEmancipated);

        if  (canDrive) {
         System.out.printf("%s, você pode dirigir \n", name);
        } else {
         System.out.printf("%s você não pode dirigir \n", name);
     
    }

    }
}
  
    
    

