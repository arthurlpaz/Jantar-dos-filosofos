package JantarDosFilosofos;

import java.util.concurrent.locks.Lock;

public class Filosofo implements Runnable {
    private final int id;
    private Lock garfoEsquerdo;
    private Lock garfoDireito;

    public Filosofo(int id, Lock garfoEsquerdo, Lock garfoDireito){
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    public void run(){
        while(true) {
            pensar();

            garfoEsquerdo.lock(); // tenta pegar o garfo da esquerda

            boolean locked = garfoDireito.tryLock(); //tenta pegar o garfo da direita


            // se não conseguir pegar o garfo da direita, sai do loop
            if (locked) {
                break;
            }

            // se não conseguir pegar o garfo da direita, libera o garfo da esquerda
            garfoEsquerdo.unlock();

            // faz a troca de garfos
            trocarGarfos();
        }

        comer();

        // faz a liberação dos garfos
        garfoDireito.unlock();
        garfoEsquerdo.unlock();
    }

    private void pensar() {
        System.out.println("Filósofo "+ (id + 1) + " está pensando.");
    }

    private void comer() {
        System.out.println("Filósofo "+ (id + 1) + " está comendo.");
    }

    private void trocarGarfos() {
        System.out.println("Filosofo " + (id + 1) + " trocou os garfos.");

        Lock auxiliar = garfoEsquerdo;
        garfoEsquerdo = garfoDireito;
        garfoDireito = auxiliar;
    }
}
