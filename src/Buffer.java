
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {

    private boolean turnoSur = false;
    private boolean turnoNorte = false;
    private boolean pasoNoDisponible = true;
    private int carrosSur = 0;
    private int carrosNorte = 0;
    private int orden = 0;
    private int nuevosCarrosNorte = 0;
    private int nuevosCarroSur = 0;

    public Buffer(int carrosSur, int carrosNorte, int orden) {
        this.carrosSur = carrosSur;
        this.carrosNorte = carrosNorte;
        this.orden = orden;
    }

    public synchronized void validarEntrada() {
        if (orden == 1) {
            this.turnoSur = false;
            this.turnoNorte = true;

        } else {
            this.turnoNorte = false;
            this.turnoSur = true;
        }
    }

    public synchronized int permitirPaso() {
        Random random = new Random();
        while (this.pasoNoDisponible) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }


        if (orden == 1) {
            if (this.carrosSur == 0) {
                this.turnoSur = true;
                int llegadaCarrosSur = random.nextInt(5) + 1;
                System.out.println("Han llegado nuevos carros del SUR "+"("+llegadaCarrosSur+")");
                this.carrosSur = llegadaCarrosSur;
                this.turnoSur = false;
                this.turnoNorte = true;
                this.pasoNoDisponible = true;
                notifyAll();

                return 1;
            }

        } else {
            if (this.carrosNorte == 0) {
                this.turnoNorte = true;
                int llegadaCarrosNorte = random.nextInt(5) + 1;
                System.out.println("Han llegado nuevos carros del NORTE "+"("+llegadaCarrosNorte+")" );
                this.carrosNorte = llegadaCarrosNorte;
                this.turnoNorte = false;
                this.turnoSur = true;
                this.pasoNoDisponible = true;
                notifyAll();

                return 2;
            }
        }
        return 0;
    }

    public synchronized int avanzarNorte() {
        while (this.turnoNorte) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        try {

            sleep(500);

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        //System.out.println("Restante norte: " + this.carrosNorte);

        //SI EL ORDEN == 1 ENTONCES VA A TERMINAR EN LOS DEL NORTE
        /*if (orden == 1) {
            if (this.carrosNorte == 0) {
                //System.out.println("Los carros del NORTE han terminado de cruzar.");
                this.turnoSur = false; //this
                this.turnoNorte = true;

                return this.carrosNorte;
            }
            //SI NO, SIGNIFICA QUE NORTE EMPEZÓ Y VA A TERMINAR CON LOS DEL SUR
        } else {
            if (this.carrosNorte == 0) {
                //System.out.println("Los carros del NORTE han terminado de cruzar.");
                this.turnoSur = false;
                this.turnoNorte = true;

                return this.carrosNorte;
            }
        } */
        this.carrosNorte = this.carrosNorte - 1;
        return this.carrosNorte;
    }

    public synchronized int avanzarSur() {
        while (this.turnoSur) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        try {

            sleep(500);

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        //System.out.println("Restante sur: " + this.carrosSur);
        //SI ORDEN == A 1, SIGNIFICA QUE LOS DEL SUR EMPEZARON, POR ENDE TERMINARÁN EN NORTE
        /* if (orden == 1) {
            if (this.carrosSur == 0) {
                //System.out.println("Los carros del SUR han terminado de cruzar.");
                this.turnoNorte = false;
                this.turnoSur = true;

                return this.carrosSur;
            }

            //SI ORDEN NO ES == 1, SIGNIFICA QUE NORTE EMPEZÓ Y VA A TERMINAR EN SUR
        } else {
            if (this.carrosSur == 0) {
                //System.out.println("Los carros del SUR han terminado de cruzar.");
                this.turnoNorte = false; //this
                this.turnoSur = true;

                return this.carrosSur;
            }

        } */
        this.carrosSur = this.carrosSur - 1;
        return this.carrosSur;
    }

    public int nuevosCarros(int orden) {

        if (orden == 1) {
            int carrosNuevoSur = 0;
            for (int i = 1; i <= this.carrosSur; i++) {
                carrosNuevoSur = i;
            }
            return carrosNuevoSur;
        } else {
            int carrosNuevoNorte = 0;
            for (int i = 1; i <= this.carrosNorte; i++) {
                carrosNuevoNorte = i;
            }
            return carrosNuevoNorte;
        }
    }

    public synchronized void carrosPasaron() {

        if (orden == 1) {
            if (this.carrosSur == 0) {
                System.out.println("Los carros del sur han terminado de pasar\n");
                this.turnoSur = true;
                this.turnoNorte = false;
                notifyAll();
                if (this.carrosNorte == 0) {
                    this.turnoNorte = true;
                    this.pasoNoDisponible = false;
                    notifyAll();
                }
                this.orden = 2;
            }

        } else {
            if (this.carrosNorte == 0) {
                System.out.println("Los carros del norte han terminado de pasar\n");
                this.turnoNorte = true;
                this.turnoSur = false;

                notifyAll();
                if (this.carrosSur == 0) {
                    this.turnoSur = true;
                    this.pasoNoDisponible = false;

                    notifyAll();
                }
                this.orden = 1;
            }

        }

    }

}
