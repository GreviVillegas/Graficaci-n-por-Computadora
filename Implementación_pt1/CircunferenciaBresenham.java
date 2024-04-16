import java.util.Arrays;

public class CircunferenciaBresenham {
     private int centroX, centroY, radio;
    private char[][] matriz;

    public CircunferenciaBresenham(int centroX, int centroY, int radio) {
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
        int x = 0, y = radio;
        int d = 3 - 2 * radio;
        simetriPuntos(x, y);
        while (y >= x) {
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            simetriPuntos(x, y);
        }
    }

    private void simetriPuntos(int x, int y) {
        if (centroX + x < 50 && centroY + y < 50) {
            matriz[centroX + x][centroY + y] = '.';
            matriz[centroX - x][centroY + y] = '.';
            matriz[centroX + x][centroY - y] = '.';
            matriz[centroX - x][centroY - y] = '.';
            matriz[centroX + y][centroY + x] = '.';
            matriz[centroX - y][centroY + x] = '.';
            matriz[centroX + y][centroY - x] = '.';
            matriz[centroX - y][centroY - x] = '.';
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
    
            CircunferenciaBresenham circunferencia = new CircunferenciaBresenham(25, 25, 10 + i);
            circunferencia.imprimirMatriz();
    
            
            tiemposej[i] = System.nanoTime() - inicioTiempo;
            totaltiemposej += tiemposej[i];
        }
    
        
        for (int i = 0; i < 40; i++) {
            System.out.println("Tiempo BRESENHAM " + (i+1) + ": " + tiemposej[i] / 1_000_000_000.0 + " segundos");
        }
    
        
        double promedio = totaltiemposej / 40.0 / 1_000_000_000.0;
        System.out.println("Tiempo promedio de ejecución: " + promedio + " segundos");
    }
}
