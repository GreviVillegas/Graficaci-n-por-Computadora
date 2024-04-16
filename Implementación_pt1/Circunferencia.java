import java.util.Arrays;
public class Circunferencia {
    private int centroX, centroY, radio;
    private char[][] matriz;

    public Circunferencia(int centroX, int centroY, int radio) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.radio = radio;
        this.matriz = new char[50][50];
        for (char[] fila : matriz) {
            Arrays.fill(fila, ' ');
        }
        dibujarCircunferencia();
    }

    private void dibujarCircunferencia() {
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                if (Math.pow(x - centroX, 2) + Math.pow(y - centroY, 2) == Math.pow(radio, 2)) {
                    matriz[x][y] = '.';
                }
            }
        }
    }

    public void imprimirMatriz() {
        for (char[] fila : matriz) {
            for (char c : fila) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
       
        long[] tiemposej = new long[40];
        long totaltiemposej = 0;
    
        for (int i = 0; i < 40; i++) {
            long inicioTiempo = System.nanoTime();
            Circunferencia circunferencia = new Circunferencia(25, 25, 10 + i);
            circunferencia.imprimirMatriz();   
            tiemposej[i] = System.nanoTime() - inicioTiempo;
            totaltiemposej += tiemposej[i];
        }
        for (int i = 0; i < 40; i++) {
            System.out.println("Tiempo ECUACIÓN " + (i+1) + ": " + tiemposej[i] / 1_000_000_000.0 + " segundos");
        }
        double promedio = totaltiemposej / 40.0 / 1_000_000_000.0;
        System.out.println("Tiempo promedio de ejecución: " + promedio + " segundos");
    }
}
