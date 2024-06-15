import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private Triangulo triangulo;

    public void setTriangulo(Triangulo triangulo) {
        this.triangulo = triangulo;
    }

    public Triangulo getTriangulo() {
        return triangulo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /* 
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= this.getWidth(); i += GRID_SIZE) {
            g.drawLine(i, 0, i, this.getHeight());
        }
        for (int i = 0; i <= this.getHeight(); i += GRID_SIZE) {
            g.drawLine(0, i, this.getWidth(), i);
        }
        */
        Color co = g.getColor();
        g.setColor(Color.WHITE);
        dibujarBorde(g, 2);
        dibujarBorde(g, 10);
        g.setColor(co);
        
        if(triangulo != null){
            int xCuarto = this.getWidth() / 4;
            int yCuarto = this.getHeight() / 4;
            int ymasMitad = ((this.getHeight() / 2) + (this.getHeight() / 4));
            int xmasMitad = ((this.getWidth() / 2) + (this.getWidth() / 4));
            int xMedio = (this.getWidth()/2);
            int tamaño = triangulo.getTamaño();

            DatoXY p1 = new DatoXY(xCuarto - tamaño, ymasMitad + tamaño);
            DatoXY p2 = new DatoXY(xmasMitad + tamaño, ymasMitad + tamaño);
            DatoXY p3 = new DatoXY(xMedio, yCuarto - tamaño);
            triangulo.setDatosTriangulo(p1, p2, p3);
            triangulo.dibujar(g);
        }
        
        // Dibujar el triangulo

        /*
         * if (circunferencia != null) {
         * g.setColor(Color.BLACK);
         * int centerX = this.getWidth() / 2;
         * int centerY = this.getHeight() / 2;
         * circunferencia.dibujar(g);
         * }
         */
    }

    private void dibujarBorde(Graphics g, int gri){
        g.drawLine(gri, gri, this.getWidth()-gri, gri);
        g.drawLine(gri, gri, gri, this.getHeight()-gri);
        g.drawLine(gri, this.getHeight()-gri, this.getWidth()-gri, this.getHeight()-gri);
        g.drawLine(this.getWidth()-gri, this.getHeight()-gri, this.getWidth()-gri, gri);
    }

}