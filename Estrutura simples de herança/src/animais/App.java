package src.animais;

import src.animais.Cachorro;

public class App {
    public static void main(String[] args) throws Exception {
        Cachorro cachorro = new Cachorro();
        cachorro.setName("Caramelo");
        cachorro.comer();
        cachorro.beber();
    }
}
