
import java.util.logging.Level;
import java.util.logging.Logger;

public class Puente extends Thread {

    private int orden = 0;
    private Buffer puente;
    private CarroNorte[] cN;
    private CarroSur[] cS;
    private int carrosNuevoSur = 0;
    private int carrosNuevoNorte = 0;

    public Puente(Buffer puente, CarroNorte[] cN, CarroSur[] cS, int orden) {
        this.cN = cN;
        this.cS = cS;
        this.puente = puente;
        this.orden = orden;
    }

    public void run() {
        while (true) {
            int retorno = puente.permitirPaso();
            int inicializarNuevosCarros = puente.nuevosCarros(retorno);

            if (retorno == 1) {
                System.out.println("LOS CARROS DEL SUR VUELVEN A PASAR");
              //  System.out.println("Inicializar nuevos carros: SUR " + inicializarNuevosCarros);
                for (int i = 0; i < inicializarNuevosCarros; i++) {
                    cS[i].setCarrosPasados(0);
                    cS[i].setBuclearCarros(0);
                    cS[i].setContador(1);
                }
            }
            if (retorno == 2) {
                System.out.println("LOS CARROS DEL NORTE VUELVEN A PASAR");
             //   System.out.println("Inicializar nuevos carros: NORTE " + inicializarNuevosCarros);
                for (int i = 0; i < inicializarNuevosCarros; i++) {
                    cN[i].setCarrosPasados(0);
                    cN[i].setBuclearCarros(0);
                    cN[i].setContador(1);
                }
            }
        }
    }

}
