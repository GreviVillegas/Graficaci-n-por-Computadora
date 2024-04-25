import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private Triangulo triangulo;
    private static final int GRID_SIZE = 10; // Tamaño de la grilla en píxeles
    private int grosorLinea = 1;
    public void setTriangulo(Triangulo triangulo) {
        this.triangulo = triangulo;
    }

    public Triangulo getTriangulo() {
        return triangulo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la grilla
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= this.getWidth(); i += GRID_SIZE) {
            g.drawLine(i, 0, i, this.getHeight());
        }
        for (int i = 0; i <= this.getHeight(); i += GRID_SIZE) {
            g.drawLine(0, i, this.getWidth(), i);
        }

        // Dibujar la circunferencia
        if (triangulo != null) {
            g.setColor(Color.BLACK);
            triangulo.dibujar(g);
        }
    }

    
public void setLineThickness(int gl) {
    this.grosorLinea = gl;
}
}