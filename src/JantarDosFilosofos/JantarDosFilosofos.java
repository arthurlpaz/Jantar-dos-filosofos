package JantarDosFilosofos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JantarDosFilosofos {
    public static void main(String[] args) {
        Lock[] garfos = new ReentrantLock[5];

        for (int i = 0; i < garfos.length; i++) {
            //cria os garfos
            garfos[i] = new ReentrantLock();
        }

        Filosofo[] filosofos = new Filosofo[5];

        for (int i = 0; i < filosofos.length; i++) {
            // cria os filósofos
            filosofos[i] = new Filosofo(i, garfos[i], garfos[(i + 1) % 5]);
        }

        for (Filosofo filosofo : filosofos) {
            // inicia cada filósofo em um thread separada
            new Thread(filosofo).start();
        }
    }
}
