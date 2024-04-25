import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Circunferencia {
    private int radio;
    private Color color;
    private Color colorRelleno = new Color(0,0,0,0);
    private String estiloLinea;
    private int grosorLinea;
    private int centerX;
    private int centerY;
    public Circunferencia(int radio, Color color) {
        this.radio = radio;
        this.color = color;
    }

    public Circunferencia(int radio, Color color, String linea, int grosorLinea) {
        this.radio = radio;
        this.color = color;
        this.estiloLinea = linea;
        this.grosorLinea = grosorLinea;
    }

    public Circunferencia(int radio, Color color, String linea, int grosorLinea, int centerX, int centerY) {
        this.radio = radio;
        this.color = color;
        this.estiloLinea = linea;
        this.grosorLinea = grosorLinea;
        this.centerX = centerX;
        this.centerY = centerY;
    }


    public void trasladar(int deltaX, int deltaY) {
        this.centerX += deltaX;
        this.centerY += deltaY;
    }

    public void setLineThickness(int lineThickness) {
        this.grosorLinea = lineThickness;
    }

    public void dibujar(Graphics g) {
        //DIBUJA EL CÍRCULO
        ArrayList<DatoXY> lista = new ArrayList<>();
        int x = 0, y = radio;
        int d = 3 - 2 * radio;

        lista.add(new DatoXY(x,y));

        while (y >= x) {
            x++;

            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            lista.add(new DatoXY(x,y));
        }
        g.setColor(color);
        dibujarCircle(lista, g);

        //RELLENA EL CÍRCULO
        fillCircle(lista, g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        if (estiloLinea.equals("Segmentado")) {
            float[] dashPattern = {10, 10};
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        } else {
            g2d.setStroke(new BasicStroke());
        }
        g2d.setStroke(new BasicStroke(grosorLinea));
        g.drawOval(centerX - radio, centerY - radio, 2 * radio, 2 * radio);
    }

    private void dibujarCircle(ArrayList<DatoXY> lista, Graphics g){
        for(int i=0;i<lista.size();i++){
            drawCircle(g, lista.get(i).getX(), lista.get(i).getY());
        }
    }

    private void fillCircle(ArrayList<DatoXY> lista, Graphics g){
        g.setColor(colorRelleno);
        boolean[][] pixeles = new boolean[centerY*2][centerX*2];
        for(int i=0;i<lista.size();i++){
            pixeles[centerY + lista.get(i).getY()][centerX + lista.get(i).getX()] = true;
            pixeles[centerY + lista.get(i).getX()][centerX + lista.get(i).getY()] = true;
            pixeles[centerY + lista.get(i).getY()][centerX - lista.get(i).getX()] = true;
            pixeles[centerY + lista.get(i).getX()][centerX - lista.get(i).getY()] = true;
            pixeles[centerY - lista.get(i).getY()][centerX + lista.get(i).getX()] = true;
            pixeles[centerY - lista.get(i).getX()][centerX + lista.get(i).getY()] = true;
            pixeles[centerY - lista.get(i).getY()][centerX - lista.get(i).getX()] = true;
            pixeles[centerY - lista.get(i).getX()][centerX - lista.get(i).getY()] = true;
        }
        g.setColor(colorRelleno);
        algoritmoInundacion(centerX, centerY, pixeles, g);
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

    public void pintar(Color colorRelleno){
        this.colorRelleno = colorRelleno;
    }

    private void drawCircle(Graphics g, int x, int y) {
        if (estiloLinea.equals("Segmentado")) {
            if (x % 2 == 0) {
                drawPoints(g, x, y);
            }
        } else {
            drawPoints(g, x, y);
        }
    }

    private void drawPoints(Graphics g, int x, int y) {
        g.fillOval(centerX + x, centerY + y, 2, 2);
        g.fillOval(centerX + y, centerY + x, 2, 2);
        g.fillOval(centerX + y, centerY - x, 2, 2);
        g.fillOval(centerX + x, centerY - y, 2, 2);
        g.fillOval(centerX - x, centerY - y, 2, 2);
        g.fillOval(centerX - y, centerY - x, 2, 2);
        g.fillOval(centerX - y, centerY + x, 2, 2);
        g.fillOval(centerX - x, centerY + y, 2, 2);
    }

}