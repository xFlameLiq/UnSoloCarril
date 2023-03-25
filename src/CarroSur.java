
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroSur extends Thread {

    private int confirmarOrden = 1;
    private int orden = 0; //1 INICIA SUR
    private Buffer carroSur;
    private int totalCarrosSur = 0;
    private int carrosPasados = 0;
    private int buclearCarros = 0;
    private int contador = 1;

    public CarroSur(Buffer buffer, int ordenAleatorio, int totalCarros) {
        this.carroSur = buffer;
        this.orden = ordenAleatorio;
        this.totalCarrosSur = totalCarros;
    }

    public void run() {
        Random random = new Random();
        carroSur.validarEntrada();
        while (true) {
            int dormirHilos = random.nextInt(3) + 1;
            int rest = random.nextInt(2000)+100;
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            if (this.carrosPasados != 1) {
                contador = carroSur.avanzarSur();
                this.carrosPasados = 1;
                if (contador != 0) {
                    int id = contador + 1;
                    System.out.println("Carro: " + "(" + this.getName() + ")" + " (SUR) está avanzando.");
                    System.out.println("Carro: " + "(" + this.getName() + ")" + "(SUR) llegó al otro lado " + id);
                } else {
                    int id = contador + 1;
                    System.out.println("Carro: " + "(" + this.getName() + ")" + " (SUR) está avanzando.");
                    System.out.println("Carro: " + "(" + this.getName() + ")" + "(SUR) llegó al otro lado " + id);
                    carroSur.carrosPasaron();
                }
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
