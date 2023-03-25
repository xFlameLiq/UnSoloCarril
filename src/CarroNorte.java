
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroNorte extends Thread {

    private int confirmarOrden = 2;
    private int orden = 0; //2 INICIA NORTE
    private Buffer carroNorte;
    private int totalCarrosNorte = 0;
    private int carrosPasados = 0;
    private int buclearCarros = 0;
    private int contador = 1;

    public CarroNorte(Buffer buffer, int ordenAleatorio, int totalCarros) {
        this.carroNorte = buffer;
        this.orden = ordenAleatorio;
        this.totalCarrosNorte = totalCarros;
    }

    public void run() {
        Random random = new Random();
        carroNorte.validarEntrada();
        while (true) {
            int dormirHilos = random.nextInt(3) + +1;
            int rest = random.nextInt(2000) + 100;
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            if (this.carrosPasados != 1) {
                contador = carroNorte.avanzarNorte();
                this.carrosPasados = 1;
                if (contador != 0) {
                    int id = contador + 1;
                    System.out.println("Carro: " + "(" + this.getName() + ")" + " (NORTE) está avanzando.");
                    System.out.println("Carro: " + "(" + this.getName() + ")" + "(NORTE) llegó al otro lado " + id);
                } else {
                    int id = contador + 1;
                    System.out.println("Carro: " + "(" + this.getName() + ")" + " (NORTE) está avanzando.");
                    System.out.println("Carro: " + "(" + this.getName() + ")" + "(NORTE) llegó al otro lado " + id);
                    carroNorte.carrosPasaron();
                }

                this.buclearCarros = 0;

            }

        }
    }

    public int getCarrosPasados() {
        return carrosPasados;
    }

    public void setCarrosPasados(int carrosPasados) {
        this.carrosPasados = carrosPasados;
    }

    public int getBuclearCarros() {
        return buclearCarros;
    }

    public void setBuclearCarros(int buclearCarros) {
        this.buclearCarros = buclearCarros;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

}
