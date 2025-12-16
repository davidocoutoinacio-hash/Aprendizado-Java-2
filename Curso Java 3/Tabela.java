
import java.io.PrintStream;





public class Tabela {

   public PrintStream imprimir = System.out;


    public static void main(String[] args) {

    Tabela tabela = new Tabela();
    tabela.imprimir.println("Tabela de Operadores Bitwise\n");
    tabela.imprimir.println("|  = OR  = Só precisa de um elemento verdadeiro para retornar uma verdade");
    tabela.imprimir.println("&  = AND = Operador AND em bitwise, precisa que ambos os elementos sejam verdadeiros para retornar uma verdade");
    tabela.imprimir.println("^  = XOR = Operador XOR em bitwise, retorna verdadeiro se os elementos forem diferentes");
    tabela.imprimir.println("~  = COMPLEMENTO = Operador complemento em bitwise, inverte os bits do número");
    tabela.imprimir.println("!  = NOT LÓGICO = Inverte o valor lógico de um booleano");
    tabela.imprimir.println("&&  = AND LÓGICO = Retorna verdadeiro se ambos os operandos forem verdadeiros");
    tabela.imprimir.println("||  = OR LÓGICO = Retorna verdadeiro se pelo menos um dos operandos for verdadeiro");


        




    }


}