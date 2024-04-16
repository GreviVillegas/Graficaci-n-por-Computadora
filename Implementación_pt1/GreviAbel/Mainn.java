package GreviAbel;
import java.lang.Math;

public class Mainn
{
    public static void main(String [] args){
        
        int dx = 18, dy = 19;
        int [][] matriz = new int[20][20];
        ListaXY bresenham = Algoritmo.bresenham(1,1,dx,dy);
        
        System.out.println("Xini=1, Yini=1, Xfin="+dx+", yFin="+dy);
        
        for(int i=0;i<bresenham.tamaño();i++){
            matriz[bresenham.dar(i)[0]][bresenham.dar(i)[1]] = 1;
        }
        
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                System.out.print((matriz[i][j]==0)?" . ":" # "+"");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        
        int [][] matriz1 = new int[20][20];
        ListaXY midPoint = Algoritmo.midPoint(1,1,dx,dy);
        
        for(int i=0;i<midPoint.tamaño();i++){
            matriz1[midPoint.dar(i)[0]][midPoint.dar(i)[1]] = 1;
        }
        
        for(int i=0;i<matriz1.length;i++){
            for(int j=0;j<matriz1[0].length;j++){
                System.out.print((matriz1[i][j]==0)?" . ":" # "+"");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("BRESENHAM");
        double tiempo = (double)System.nanoTime();
        for(int i=0;i<40;i++){
            Algoritmo.bresenham(1,1,dx,dy);
        }
        double tiempo2 = (double)System.nanoTime();
        double nanoSegundos = (tiempo2-tiempo)/Math.pow(10,9);
        String time = "0.000"+((int)((nanoSegundos * 10000)*1000)) + "";
        System.out.println("El costo de Bresenham es "+nanoSegundos+" o mejor dicho "+time);
        
        System.out.println("\n\nPUNTO MEDIO");
        tiempo = System.nanoTime();
        for(int i=0;i<40;i++){
            Algoritmo.midPoint(1,1,dx,dy);
        }
        tiempo2 = (double)System.nanoTime();
        nanoSegundos = (tiempo2-tiempo)/Math.pow(10,9);
        time = "0.000"+((int)((nanoSegundos * 10000)*1000)) + "";
        System.out.println("El costo de Punto Medio es "+nanoSegundos+" o mejor dicho "+time);
        
    }
}