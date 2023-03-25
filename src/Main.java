
import static java.lang.Thread.sleep;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random aleatorio = new Random();
        int orden = aleatorio.nextInt(2) + 1;
        int carrosNorte = 20;//aleatorio.nextInt(5)+1;
        int carrosSur = 20;//aleatorio.nextInt(5)+1;

        CarroNorte[] cN = new CarroNorte[carrosNorte];
        CarroSur[] cS = new CarroSur[carrosSur];
        Buffer intermedio = new Buffer(carrosSur, carrosNorte, orden);
        Puente puente = new Puente(intermedio, cN, cS, orden);
        puente.start();

        for (int i = 0; i < cN.length; i++) {
            cN[i] = new CarroNorte(intermedio, orden, carrosNorte);
        }
        for (int i = 0; i < cS.length; i++) {
            cS[i] = new CarroSur(intermedio, orden, carrosSur);
        }
        //carros.imprimirArray();
        System.out.println(carrosNorte + " carros del NORTE han llegado");
        System.out.println(carrosSur + " carros del SUR han llegado");
        
        int rest = aleatorio.nextInt(3)+1;
        if (orden == 1) {
            System.out.println("SUR HA INICIADO" + "(" + orden + ")");
            for (int i = 0; i < cS.length; i++) {
                cS[i].start();
                sleep(300);
            }

            for (int i = 0; i < cN.length; i++) {
                cN[i].start();
                sleep(300);
            }
        } else {
            System.out.println("NORTE HA INICIADO" + "(" + orden + ")");
            for (int i = 0; i < cN.length; i++) {
                cN[i].start();
                sleep(300);
            }
            for (int i = 0; i < cS.length; i++) {
                cS[i].start();
                sleep(300);
            }
        }
    }

}
