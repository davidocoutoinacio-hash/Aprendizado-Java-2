public class Main {
    public static void main(String[] args) {
        
        var volue1 = 12;
        var binary1 = Integer.toBinaryString(volue1);
        System.out.printf("Primeiro número da operação %s (representação binária %s)\n", volue1, binary1);
        var volue2 = 2;
        //var binary2 = Integer.toBinaryString(volue2);
        System.out.printf("Segundo número da operação %s\n", volue2);
        var result = volue1 >>> volue2;
        var binaryResult = Integer.toBinaryString(result);
        System.out.printf(" %s >>> %s = %s (representação binária %s)\n", volue1, volue2, result, binaryResult); 
    }


    /*
    | = or = só precisa de um elemento verdadeiro para retornar uma verdade
    & = operador end em bity wise, 
    ^ = operador xor em bity wise
    ~ = operador complement em bity wise, so precisa de um elemento para fazer isso de um operador, 
    funciona que nem o not
    ! = Not logico
    Laft Shift Operator = << = deslocamento de bytes para a esquerda, quando desloca 2 para a esquerda acaba deixando 2 zeros a direita
    Right Shift Operetor = >> = deslocamento de bytes para a direita
    Unsigned Right Shift = >>> = deslocamento para a direita sem sinal, preenchendo com zeros



    Right Shift Operetor verifica o bit mais a direita e desloca os bits para a direita,
    o bit mais a direita é descartado e o bit mais a esquerda é preenchido com 0
    Exemplo:
    00000000000000000000000000001001 = 9
    00000000000000000000000000000010 = 2
    --------------------------------


    Xor compara cada bite, se os numeros sao iguais retorna 0, se forem diferentes retorna 1

    0 = false
    1 = true

    1111111111111111111111111111111 NUMERO MAXIMO INTEIRO
    0000000000000000000000000000110 ZEROS A ESQUERDA NÃO SÃO CONSIDERADOS
    0000000000000000000000000000010 
    0000000000000000000000000000101
    0000000000000000000000000000111
    1000 = 8

    System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

    0000000000000000000000000000110

    */






}