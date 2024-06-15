import java.awt.Color;
import java.awt.Graphics;

public class Triangulo {
    private int tamaño;
    private Color color = Color.DARK_GRAY;
    private int nivel;
    private DatoXY p1, p2, p3;

    public Triangulo(int tamaño){
        this.tamaño = tamaño;
        p1 = new DatoXY();
        p2 = new DatoXY();
        p3 = new DatoXY();
        this.nivel = 0;
    }

    public void setDatosTriangulo(DatoXY p1, DatoXY p2, DatoXY p3Alto){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3Alto;
    }

    public DatoXY getP1(){
        return p1;
    }
    public DatoXY getP2(){
        return p2;
    }
    public DatoXY getP3(){
        return p3;
    }

    public void setFractal(int nivel){
        this.nivel = nivel;
    }

    public void setTamaño(int tamaño){
        this.tamaño = tamaño;
    }
    public int getTamaño(){
        return tamaño;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void dibujar(Graphics g){
        Color antiguo = g.getColor();
        g.setColor(color);
        g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        g.drawLine(p3.getX(), p3.getY(), p1.getX(), p1.getY());
        g.drawLine(p3.getX(), p3.getY(), p2.getX(), p2.getY());

        if(nivel > 0) dibujarFractal(g);;
        g.setColor(antiguo);
    }


    private void dibujarFractal(Graphics g){
        Triangulo fractal = crearFractal(g);
        fractal.setColor(color);
        fractal.dibujar(g);
        if((nivel-1) > 0){
            Triangulo t1 = crearTriangulo(fractal.p1, fractal.p2, p3, nivel-1);
            Triangulo t2 = crearTriangulo(p1, fractal.p3, fractal.p1, nivel-1);
            Triangulo t3 = crearTriangulo(fractal.p3, p2, fractal.p2, nivel-1);
            t1.dibujarFractal(g);
            t2.dibujarFractal(g);
            t3.dibujarFractal(g);
        }
    }

    private Triangulo crearTriangulo(DatoXY p1, DatoXY p2, DatoXY p3, int nivel){
        Triangulo res = new Triangulo(tamaño);
        res.setDatosTriangulo(p1, p2, p3);
        res.setFractal(nivel);
        res.setColor(color);
        return res;
    }

    private Triangulo crearFractal(Graphics g){
        Triangulo mitad = new Triangulo(tamaño);
        DatoXY fP1 = puntosMitad(p1,p3);
        DatoXY fP2 = puntosMitad(p2,p3);
        DatoXY fP3 = puntosMitad(p1,p2);

        fP2.setY(fP1.getY()); //Para evitar inclinaciones
        mitad.setDatosTriangulo(fP1, fP2, fP3);
        return mitad;
    }

    private DatoXY puntosMitad(DatoXY p1, DatoXY p2){
        int mitadX = (p2.getX() - p1.getX())/2;
        //if(mitadX < 0) mitadX = -mitadX;
        DatoXY res = new DatoXY(p1.getX() + mitadX, 
        (int)(funcionRectaX(p1, p2, p1.getX()+mitadX)));

        return res;
    }

    private double funcionRectaX(DatoXY p1, DatoXY p2, int puntoX){
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double m = dy/dx;
        
        return ((puntoX - p1.getX()) * m) + p1.getY();   // y - y1 = (x - x1) m
    }

    
}
