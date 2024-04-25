import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Triangulo {
    private DatoXY pos1;
    private DatoXY pos2;
    private DatoXY pos3;

    private int xPanel, yPanel;

    private Color color;
    private Color colorRelleno;
    private int grosorLinea;

    public Triangulo(DatoXY pos1, DatoXY pos2, DatoXY pos3, Color color, int xPanel, int yPanel){
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.pos3 = pos3;
        this.color = Color.BLACK;
        this.colorRelleno = color;
        grosorLinea = 1;
        this.xPanel = xPanel;
        this.yPanel = yPanel;
    }

    public void setGrosorLinea(int grosorLinea){
        this.grosorLinea = grosorLinea;
    }

    public void setTraslado(int valorX, int valorY){
        pos1.setX(pos1.getX() + valorX);
        pos1.setY(pos1.getY() + valorY);
        pos2.setX(pos2.getX() + valorX);
        pos2.setY(pos2.getY() + valorY);
        pos3.setX(pos3.getX() + valorX);
        pos3.setY(pos3.getY() + valorY);
    }

    public void dibujar(Graphics g){
        ArrayList<DatoXY> lista = new ArrayList<>();

        drawLineBresenham(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY(), lista);
        drawLineBresenham(pos1.getX(), pos1.getY(), pos3.getX(), pos3.getY(), lista);
        drawLineBresenham(pos2.getX(), pos2.getY(), pos3.getX(), pos3.getY(), lista);
        fillTriangulo(lista,g);
        g.setColor(color);
        dibujarTriangulo(lista, g);
    }

    public void cambiarTamaño(double tamaño){
        pos2.setX((int) (pos2.getX()*tamaño));
        pos2.setY((int) (pos2.getY()*tamaño));

        pos3.setX((int) (pos3.getX()*tamaño));
        pos3.setY((int) (pos3.getY()*tamaño));
    }

    private void dibujarTriangulo(ArrayList<DatoXY> lista, Graphics g){
        for(int i=0;i<lista.size();i++){
            g.fillRect(lista.get(i).getX(), lista.get(i).getY(),2*grosorLinea,2*grosorLinea);
        }
    }

    private void drawLineBresenham(int x1, int y1, int x2, int y2, ArrayList<DatoXY> lista) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int x = x1;
        int y = y1;

        while (true) {
            lista.add(new DatoXY(x,y));

            if (x == x2 && y == y2) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }
    private void fillTriangulo(ArrayList<DatoXY> lista, Graphics g){
        g.setColor(colorRelleno);
        boolean[][] pixeles = new boolean[yPanel][xPanel];
        for(int i=0;i<lista.size();i++){
            pixeles[lista.get(i).getY()][lista.get(i).getX()] = true;
        }
        g.setColor(colorRelleno);
        ArrayList<DatoXY> dato1 = new ArrayList<>();
        ArrayList<DatoXY> dato2 = new ArrayList<>();
        drawLineBresenham(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY(), dato1);
        drawLineBresenham(dato1.get(dato1.size()/2).getX(), dato1.get(dato1.size()/2).getY(), pos3.getX(), 
            pos3.getY(), dato2);

        algoritmoInundacion(dato2.get(dato2.size()/2).getX(), dato2.get(dato2.size()/2).getY(), pixeles, g);
        //g.fillOval(centerX-radio, centerY-radio,radio*2,radio*2);
    }

    private void algoritmoInundacion(int x, int y, boolean[][] pixeles, Graphics g){
        int contador = 0;
        DatoXY datos;
        Stack<DatoXY> pila = new Stack<>();
        pila.push(new DatoXY(x,y));
        while(!pila.isEmpty() && contador < 1000000){
            datos = pila.pop();
            x = datos.getX();
            y = datos.getY();
            contador++;
            if(x>=0 && y>=0 && x < pixeles[0].length && y < pixeles.length && !pixeles[datos.getY()][datos.getX()]){
                g.fillRect(x, y, 1, 1);
                pixeles[y][x] = true;
                pila.push(new DatoXY(x+1,y));
                pila.push(new DatoXY(x,y+1));
                pila.push(new DatoXY(x-1,y));
                pila.push(new DatoXY(x,y-1));
            }
        }
        /* 
        if(x>=0 && y>=0 && x < pixeles.length && y < pixeles.length && contador < 7000){
            if(!pixeles[y][x]){
                g.fillRect(x,y,1,1);
                pixeles[y][x] = true;
                algoritmoInundacion(x+1,y,pixeles,g, contador+1);
                algoritmoInundacion(x,y+1,pixeles,g, contador+1);
                algoritmoInundacion(x-1,y,pixeles,g, contador+1);
                algoritmoInundacion(x,y-1,pixeles,g, contador+1);
            }
        }
        */
    }
}
